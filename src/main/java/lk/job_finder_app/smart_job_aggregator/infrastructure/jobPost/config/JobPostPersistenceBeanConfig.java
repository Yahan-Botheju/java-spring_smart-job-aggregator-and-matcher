package lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.config;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.JobPostRepositoryImpl;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.jpa.JpaJobPostRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.mapper.JobPostPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JobPostPersistenceBeanConfig {
    @Bean
    JobPostRepository jobPostRepository(
            JpaJobPostRepository jobPostRepository,
            JobPostPersistenceMapper jobPostPersistenceMapper
    ) {
        return new JobPostRepositoryImpl(jobPostRepository, jobPostPersistenceMapper);
    }
}
