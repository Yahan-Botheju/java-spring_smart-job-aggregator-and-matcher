package lk.job_finder_app.smart_job_aggregator.usecase.jobApplication;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JobApplicationUseCaseImpl implements JobApplicationUseCase {

    //inject job application repo
    private final JobApplicationRepository jobApplicationRepository;

    //inject repositories (COMPANY, JOB-POST, USER)
    private final CompanyRepository companyRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;


}
