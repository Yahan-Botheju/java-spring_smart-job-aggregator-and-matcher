package lk.job_finder_app.smart_job_aggregator.usecase.jobApplication;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobApplicationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JobApplicationUseCaseImpl implements JobApplicationUseCase {

    //inject job application repo
    private final JobApplicationRepository jobApplicationRepository;
}
