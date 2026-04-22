package com.example.Ex.Repository;

import com.example.Ex.DBConnection;
import com.example.Ex.Entity.*;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        String cashSql = "SELECT id, amount FROM cash_accounts WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(cashSql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CashAccount account = new CashAccount();
                    account.setId(rs.getString("id"));
                    account.setAmount(rs.getDouble("amount"));
                    return account;
                }
            }
        }


        String mobileSql = "SELECT id, holder_name, mobile_banking_service, mobile_number, amount FROM mobile_banking_accounts WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(mobileSql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MobileBankingAccount account = new MobileBankingAccount();
                    account.setId(rs.getString("id"));
                    account.setHolderName(rs.getString("holder_name"));
                    account.setMobileBankingService(rs.getString("mobile_banking_service"));
                    account.setMobileNumber(rs.getString("mobile_number"));
                    account.setAmount(rs.getDouble("amount"));
                    return account;
                }
            }
        }


        String bankSql = "SELECT id, holder_name, bank_name, bank_code, bank_branch_code, bank_account_number, bank_account_key, amount FROM bank_accounts WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(bankSql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    BankAccount account = new BankAccount();
                    account.setId(rs.getString("id"));
                    account.setHolderName(rs.getString("holder_name"));
                    account.setBankName(rs.getString("bank_name"));
                    account.setBankCode(rs.getInt("bank_code"));
                    account.setBankBranchCode(rs.getInt("bank_branch_code"));
                    account.setBankAccountNumber(rs.getInt("bank_account_number"));
                    account.setBankAccountKey(rs.getInt("bank_account_key"));
                    account.setAmount(rs.getDouble("amount"));
                    return account;
                }
            }
        }

        return null;
    }

    public void updateAccountAmount(String id, double newAmount) throws SQLException {
        String sql1 = "UPDATE cash_accounts SET amount = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql1)) {
            ps.setDouble(1, newAmount);
            ps.setString(2, id);
            int updated = ps.executeUpdate();
            if (updated > 0) return;
        }

        String sql2 = "UPDATE mobile_banking_accounts SET amount = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql2)) {
            ps.setDouble(1, newAmount);
            ps.setString(2, id);
            int updated = ps.executeUpdate();
            if (updated > 0) return;
        }

        String sql3 = "UPDATE bank_accounts SET amount = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql3)) {
            ps.setDouble(1, newAmount);
            ps.setString(2, id);
            ps.executeUpdate();
        }
    }
}