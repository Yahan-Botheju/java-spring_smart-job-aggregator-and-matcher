package lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.config;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.JobApplicationRepositoryImpl;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.jpa.JpaJobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.mapper.JobApplicationPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JobApplicationPersistenceBeanConfig implements JobApplicationRepository {
    @Bean
    JobApplicationRepository jobApplicationRepository(
            JpaJobApplicationRepository jpaJobApplicationRepository,
            JobApplicationPersistenceMapper jobApplicationPersistenceMapper
    ) {
        return new JobApplicationRepositoryImpl(jpaJobApplicationRepository, jobApplicationPersistenceMapper);
    }
}
