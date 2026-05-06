package lk.job_finder_app.smart_job_aggregator.usecase.jobApplication;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobApplication;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobApplicationAggregate;

import java.util.List;

public interface JobApplicationUseCase {
    //get all job applications
    List<JobApplicationAggregate> getAllJobApplications();

    //create job application
    JobApplicationAggregate createJobApplication(JobApplication jobApplication);
}
