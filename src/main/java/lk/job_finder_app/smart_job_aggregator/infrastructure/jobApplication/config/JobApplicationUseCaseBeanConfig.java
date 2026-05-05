package lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.config;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lk.job_finder_app.smart_job_aggregator.usecase.jobApplication.JobApplicationUseCase;
import lk.job_finder_app.smart_job_aggregator.usecase.jobApplication.JobApplicationUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JobApplicationUseCaseBeanConfig {
    @Bean
    JobApplicationUseCase jobApplicationUseCase(
            JobApplicationRepository jobApplicationRepository,
            CompanyRepository companyRepository,
            JobPostRepository jobPostRepository,
            UserRepository userRepository
    ) {
        return new JobApplicationUseCaseImpl(
                jobApplicationRepository,
                companyRepository,
                jobPostRepository,
                userRepository
        );
    }
}
