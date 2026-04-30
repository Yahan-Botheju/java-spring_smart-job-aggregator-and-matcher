package lk.job_finder_app.smart_job_aggregator.usecase.company;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyUseCaseImpl implements CompanyUseCase {

    //inject company repository
    private final CompanyRepository companyRepository;
}
