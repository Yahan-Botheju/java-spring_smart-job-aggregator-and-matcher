package lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence;

import lk.job_finder_app.smart_job_aggregator.domain.models.Company;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence.entity.CompanyEntity;
import lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence.jpa.JpaCompanyRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence.mapper.CompanyPersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

    //inject jpa repo
    private final JpaCompanyRepository jpaCompanyRepository;

    //inject persistence mapper
    private final CompanyPersistenceMapper companyPersistenceMapper;

    //check company by ID
    @Override
    public Optional<Company> getCompanyById(long companyId) {
        return jpaCompanyRepository.findById(companyId)
                .map(companyPersistenceMapper::toDomainModel);

    }

    //get all companies
    @Override
    public List<Company> getAllCompanies(){
        //get all details from db
        List<CompanyEntity > companyEntities = jpaCompanyRepository.findAll();
        //turn entity to domain model and return
        return companyEntities.stream().map(companyPersistenceMapper::toDomainModel).toList();
    }

    //create company
    @Override
    public Company createCompany(Company company){
        //domain model to entity
        CompanyEntity companyEntity = companyPersistenceMapper.toEntity(company);
        //save in db
        CompanyEntity savedEntity = jpaCompanyRepository.save(companyEntity);
        //turn to domain model and return
        return companyPersistenceMapper.toDomainModel(savedEntity);
    }
}
