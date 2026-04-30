package lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.jpa.JpaJobPostRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.mapper.JobPostPersistenceMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JobPostRepositoryImpl implements JobPostRepository {

    //inject jpa repo
    private final JpaJobPostRepository jpaJobPostRepository;

    //inject persistence mapper
    private final JobPostPersistenceMapper jobPostPersistenceMapper;
}
