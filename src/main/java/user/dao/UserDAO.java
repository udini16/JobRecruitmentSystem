package user.dao;

import db.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Validate user credentials
    public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM applicant WHERE username = ? AND password = ?";
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

    // Register a new user
    public boolean registerUser(String firstName, String username, String email, String password) {
        String query = "INSERT INTO applicant (firstName, username, email, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, firstName);
            stmt.setString(2, username);
            stmt.setString(3, email);
            stmt.setString(4, password); // Hash the password before storing it

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Returns true if insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get the first name of a user
    public String getFirstName(String username) {
        String query = "SELECT firstName FROM applicant WHERE username = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("firstName"); // Returns the first name of the user
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Returns null if no user found or an error occurs
    }
}
