package lk.job_finder_app.smart_job_aggregator.usecase.company;

import lk.job_finder_app.smart_job_aggregator.domain.models.Company;

import java.util.List;

public interface CompanyUseCase {

    //get all companies
    List<Company> getAllCompanies();
}
