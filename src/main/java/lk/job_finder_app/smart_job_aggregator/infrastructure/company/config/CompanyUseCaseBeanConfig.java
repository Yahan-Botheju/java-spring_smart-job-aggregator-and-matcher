package lk.job_finder_app.smart_job_aggregator.infrastructure.company.config;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.usecase.company.CompanyUseCase;
import lk.job_finder_app.smart_job_aggregator.usecase.company.CompanyUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CompanyUseCaseBeanConfig {
    @Bean
    CompanyUseCase companyUseCase(CompanyRepository companyRepository) {
        return new CompanyUseCaseImpl(companyRepository);
    }
}
