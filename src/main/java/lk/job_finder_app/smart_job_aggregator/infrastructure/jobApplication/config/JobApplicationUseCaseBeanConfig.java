package lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.config;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.usecase.jobApplication.JobApplicationUseCase;
import lk.job_finder_app.smart_job_aggregator.usecase.jobApplication.JobApplicationUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JobApplicationUseCaseBeanConfig {
    @Bean
    JobApplicationUseCase jobApplicationUseCase(
            JobApplicationRepository jobApplicationRepository
    ) {
        return new JobApplicationUseCaseImpl(jobApplicationRepository);
    }
}
