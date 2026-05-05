package lk.job_finder_app.smart_job_aggregator.domain.repositories;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobApplication;

import java.util.Optional;

public interface JobApplicationRepository {
    //helper methods
    Optional<JobApplication> jobApplicationFindById(long jobApplicationId);
}
