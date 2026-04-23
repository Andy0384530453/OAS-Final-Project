package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FinancialAccountRepository {

    private final DBConnection dbConnection;

    public FinancialAccountRepository(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private Connection getConnection() throws SQLException {
        return dbConnection.getDBConnection();
    }

    public FinancialAccount findById(String id) throws SQLException {


        String typeSql = "SELECT type, amount FROM financial_accounts WHERE id = ?";
        String type = null;
        double amount = 0;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(typeSql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    type = rs.getString("type");
                    amount = rs.getDouble("amount");
                } else {
                    return null;
                }
            }
        }

        if ("CASH".equals(type)) {
            CashAccount account = new CashAccount();
            account.setId(id);
            account.setAmount(amount);
            return account;
        }
        else if ("MOBILE_BANKING".equals(type)) {
            String mobileSql = "SELECT holder_name, mobile_banking_service, mobile_number FROM mobile_banking_accounts WHERE id = ?";
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(mobileSql)) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        MobileBankingAccount account = new MobileBankingAccount();
                        account.setId(id);
                        account.setAmount(amount);
                        account.setHolderName(rs.getString("holder_name"));
                        account.setMobileBankingService(rs.getString("mobile_banking_service"));
                        account.setMobileNumber(rs.getString("mobile_number"));
                        return account;
                    }
                }
            }
        }
        else if ("BANK".equals(type)) {
            String bankSql = "SELECT holder_name, bank_name, bank_code, bank_branch_code, bank_account_number, bank_account_key FROM bank_accounts WHERE id = ?";
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(bankSql)) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        BankAccount account = new BankAccount();
                        account.setId(id);
                        account.setAmount(amount);
                        account.setHolderName(rs.getString("holder_name"));
                        account.setBankName(rs.getString("bank_name"));
                        account.setBankCode(rs.getInt("bank_code"));
                        account.setBankBranchCode(rs.getInt("bank_branch_code"));
                        account.setBankAccountNumber(rs.getInt("bank_account_number"));
                        account.setBankAccountKey(rs.getInt("bank_account_key"));
                        return account;
                    }
                }
            }
        }

        return null;
    }

    public void updateAccountAmount(String id, double newAmount) throws SQLException {
        String sql = "UPDATE financial_accounts SET amount = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, newAmount);
            ps.setString(2, id);
            ps.executeUpdate();
        }
    }

    public List<String> findAccountIdsByCollectivityId(String collectivityId) throws SQLException {
        List<String> accountIds = new ArrayList<>();

        String sql = """
            SELECT DISTINCT fa.id
            FROM financial_accounts fa
            WHERE fa.id IN (
                SELECT DISTINCT ct.account_credited_id 
                FROM collectivity_transactions ct 
                WHERE ct.collectivity_id = ?
            )
        """;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, collectivityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    accountIds.add(rs.getString("id"));
                }
            }
        }

        return accountIds;
    }
}