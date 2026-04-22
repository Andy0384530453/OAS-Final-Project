package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.CollectivityTransaction;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityTransactionRepository {

    private final DBConnection dbConnection;

    public CollectivityTransactionRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private Connection getConnection() throws SQLException {
        return dbConnection.getDBConnection();
    }

    public void createTransaction(CollectivityTransaction transaction) throws SQLException {
        String sql = "INSERT INTO collectivity_transactions (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, transaction.getId());
            ps.setString(2, transaction.getCollectivityId());
            ps.setDate(3, Date.valueOf(transaction.getCreationDate()));
            ps.setDouble(4, transaction.getAmount());
            ps.setString(5, transaction.getPaymentMode());
            ps.setString(6, transaction.getAccountCreditedId());
            ps.setString(7, transaction.getMemberDebitedId());
            ps.executeUpdate();
        }
    }

    public List<CollectivityTransaction> findByCollectivityIdAndDateRange(String collectivityId, String from, String to) throws SQLException {
        List<CollectivityTransaction> transactions = new ArrayList<>();
        String sql = "SELECT id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id FROM collectivity_transactions WHERE collectivity_id = ? " +
                "AND creation_date BETWEEN ? AND ? " +
                "ORDER BY creation_date";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            ps.setDate(2, Date.valueOf(from));
            ps.setDate(3, Date.valueOf(to));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transactions.add(new CollectivityTransaction(
                            rs.getString("id"),
                            rs.getString("collectivity_id"),
                            rs.getDate("creation_date") != null ? rs.getDate("creation_date").toString() : null,
                            rs.getDouble("amount"),
                            rs.getString("payment_mode"),
                            rs.getString("account_credited_id"),
                            rs.getString("member_debited_id")
                    ));
                }
            }
        }
        return transactions;
    }
}