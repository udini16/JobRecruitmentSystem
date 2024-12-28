package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.connection.ConnectionManager;

public class CompanyDAO {

    public boolean insertCompany(String companyName, String username, String password, String email,
                                 String phoneNumber, String address, String industryType,
                                 String registrationDate, String website) {
        String sql = "INSERT INTO company (companyName, username, password, email, phoneNumber, " +
                     "address, industryType, registrationDate, website) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, companyName);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, email);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, address);
            pstmt.setString(7, industryType);
            pstmt.setString(8, registrationDate);
            pstmt.setString(9, website);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean validateCompanyUser(String username, String password) {
        String sql = "SELECT * FROM company WHERE username = ? AND password = ?";
        
        try (Connection conn = ConnectionManager.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            return pstmt.executeQuery().next(); // True if a match is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String getCompanyName(String username) {
        String query = "SELECT companyName FROM company WHERE username = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("companyName"); // Returns the company name of the user
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Returns null if no user found or an error occurs
    }
}
