package lk.job_finder_app.smart_job_aggregator.infrastructure.company.config;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence.CompanyRepositoryImpl;
import lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence.jpa.JpaCompanyRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence.mapper.CompanyPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CompanyPersistenceBeanConfig {
    @Bean
    CompanyRepository companyRepository(
            JpaCompanyRepository jpaCompanyRepository,
            CompanyPersistenceMapper companyPersistenceMapper
    ) {
        return new CompanyRepositoryImpl(jpaCompanyRepository, companyPersistenceMapper);
    }
}
