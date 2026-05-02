package lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.config;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lk.job_finder_app.smart_job_aggregator.usecase.jobPost.JobPostUseCase;
import lk.job_finder_app.smart_job_aggregator.usecase.jobPost.JobPostUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JobPostUseCaseBeanConfig {
    @Bean
    JobPostUseCase jobPostUseCase(
            JobPostRepository jobPostRepository,
            CompanyRepository companyRepository
    ) {
        return new JobPostUseCaseImpl(jobPostRepository, companyRepository);
    }
}
