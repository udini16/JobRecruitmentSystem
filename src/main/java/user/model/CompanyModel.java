package user.model;

import db.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CompanyModel {
	
	public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM company WHERE username = ? AND password = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if user exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	public boolean registerCompany(String companyName, String username, String password, String email, String phoneNumber, String address, String industryType, String registrationDate, String website) {
        String query = "INSERT INTO company (companyName, username, password, email, phoneNumber, address, industryType, registrationDate, website) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, companyName);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, email); 
            stmt.setString(5, phoneNumber);
            stmt.setString(6, address);
            stmt.setString(7, industryType);
            stmt.setString(8, registrationDate);
            stmt.setString(9, website);

            int rowsInserted = stmt.executeUpdate();	
            return rowsInserted > 0; // If insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
