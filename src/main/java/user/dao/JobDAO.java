package user.dao;

import db.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JobDAO {

	public boolean insertJob(String jobTitle, String description, String requirement, String postingDate, String deadline, String companyName) {
	    String insertJobSql = "INSERT INTO job (jobTitle, description, requirement, postingDate, deadline) VALUES (?, ?, ?, ?, ?)";
	    String findCompanyIdSql = "SELECT companyID FROM company WHERE companyName = ?";
	    String insertJobOpportunitySql = "INSERT INTO jobOpportunities (companyID, jobID) VALUES (?, ?)";

	    Connection conn = null;
	    PreparedStatement jobStmt = null;
	    PreparedStatement companyStmt = null;
	    PreparedStatement jobOpportunityStmt = null;

	    try {
	        conn = ConnectionManager.getConnection();
	        conn.setAutoCommit(false); // Start transaction

	        // Step 1: Insert job into the Job table
	        jobStmt = conn.prepareStatement(insertJobSql, PreparedStatement.RETURN_GENERATED_KEYS);
	        jobStmt.setString(1, jobTitle);
	        jobStmt.setString(2, description);
	        jobStmt.setString(3, requirement);
	        jobStmt.setString(4, postingDate);
	        jobStmt.setString(5, deadline);
	        jobStmt.executeUpdate();

	        // Retrieve the generated jobID
	        int jobID = -1;
	        try (var rs = jobStmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                jobID = rs.getInt(1);
	            }
	        }

	        if (jobID == -1) {
	            throw new SQLException("Failed to retrieve generated jobID.");
	        }

	        // Step 2: Get the companyID from the company table using companyName
	        companyStmt = conn.prepareStatement(findCompanyIdSql);
	        companyStmt.setString(1, companyName);
	        int companyID = -1;
	        try (var rs = companyStmt.executeQuery()) {
	            if (rs.next()) {
	                companyID = rs.getInt("companyID");
	            }
	        }

	        if (companyID == -1) {
	            throw new SQLException("Company not found for the provided companyName.");
	        }

	        // Step 3: Link the job to the company in the JobOpportunities table
	        jobOpportunityStmt = conn.prepareStatement(insertJobOpportunitySql);
	        jobOpportunityStmt.setInt(1, companyID);
	        jobOpportunityStmt.setInt(2, jobID);
	        jobOpportunityStmt.executeUpdate();

	        conn.commit(); // Commit transaction
	        return true;
	    } catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback(); // Rollback transaction on error
	            } catch (SQLException rollbackEx) {
	                rollbackEx.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	    } finally {
	        // Close resources
	        try {
	            if (jobStmt != null) jobStmt.close();
	            if (companyStmt != null) companyStmt.close();
	            if (jobOpportunityStmt != null) jobOpportunityStmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
	    }
	    return false;
	}

}
