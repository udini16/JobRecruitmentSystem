package user.model;

import user.dao.JobDAO;

public class JobModel {
    private JobDAO jobDAO;

    public JobModel() {
        this.jobDAO = new JobDAO();
    }

    public boolean postJob(String jobTitle, String description, String requirement, String postingDate, String deadline, String companyName) {
        return jobDAO.insertJob(jobTitle, description, requirement, postingDate, deadline, companyName);
    }
}
