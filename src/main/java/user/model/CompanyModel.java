package user.model;

import user.dao.CompanyDAO;

public class CompanyModel {
    private CompanyDAO companyDAO;

    public CompanyModel() {
        this.companyDAO = new CompanyDAO();
    }

    public boolean registerCompany(String companyName, String username, String password, String email,
                                   String phoneNumber, String address, String industryType,
                                   String registrationDate, String website) {
        return companyDAO.insertCompany(companyName, username, password, email, phoneNumber, address, industryType, registrationDate, website);
    }
    public boolean validateCompanyUser(String username, String password) {
        return companyDAO.validateCompanyUser(username, password);
    }
    public String getCompanyName(String companyName) {
        return companyDAO.getCompanyName(companyName);
    }
}
