package lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence.jpa.JpaCompanyRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence.mapper.CompanyPersistenceMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

    //inject jpa repo
    private final JpaCompanyRepository jpaCompanyRepository;

    //inject persistence mapper
    private final CompanyPersistenceMapper companyPersistenceMapper;
}
