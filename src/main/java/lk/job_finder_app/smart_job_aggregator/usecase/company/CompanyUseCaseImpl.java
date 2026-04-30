package lk.job_finder_app.smart_job_aggregator.usecase.company;

import lk.job_finder_app.smart_job_aggregator.domain.models.Company;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CompanyUseCaseImpl implements CompanyUseCase {

    //inject company repository
    private final CompanyRepository companyRepository;

    //get all companies
    @Override
    public List<Company> getAllCompanies(){
        return  companyRepository.getAllCompanies();
    }
}
