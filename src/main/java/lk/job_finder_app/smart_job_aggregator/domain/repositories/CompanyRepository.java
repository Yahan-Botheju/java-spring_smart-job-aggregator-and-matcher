package lk.job_finder_app.smart_job_aggregator.domain.repositories;

import lk.job_finder_app.smart_job_aggregator.domain.models.Company;

import java.util.List;

public interface CompanyRepository {

    //get all companies
    List<Company> getAllCompanies();
}
