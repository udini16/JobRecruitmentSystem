package user.model;

import user.dao.UserDAO;

public class UserModel {

    private UserDAO userDAO = new UserDAO();

    // Validate user credentials
    public boolean validateUser(String username, String password) {
        return userDAO.validateUser(username, password);
    }

    // Register a new user
    public boolean registerUser(String firstName, String username, String email, String password) {
        return userDAO.registerUser(firstName, username, email, password);
    }

    // Get the first name of a user
    public String getFirstName(String username) {
        return userDAO.getFirstName(username);
    }
}
