package lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.jpa.JpaJobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.mapper.JobApplicationPersistenceMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JobApplicationRepositoryImpl implements JobApplicationRepository {

    //inject job application jpa
    private final JpaJobApplicationRepository jpaJobApplicationRepository;

    //inject persistence mapper
    private final JobApplicationPersistenceMapper jobApplicationPersistenceMapper;
}
