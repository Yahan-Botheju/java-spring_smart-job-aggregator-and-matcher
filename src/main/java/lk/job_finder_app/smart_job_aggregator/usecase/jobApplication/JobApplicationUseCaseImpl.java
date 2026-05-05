package lk.job_finder_app.smart_job_aggregator.usecase.jobApplication;

import lk.job_finder_app.smart_job_aggregator.domain.models.Company;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.domain.models.User;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JobApplicationUseCaseImpl implements JobApplicationUseCase {

    //inject job application repo
    private final JobApplicationRepository jobApplicationRepository;

    //inject repositories (COMPANY, JOB-POST, USER)
    private final CompanyRepository companyRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    /* ----- HELPER METHODS ----- */

    //create methods for getting details (COMPANY, JOB-POST, USER)

    private Company getCompanyDetailsById(Long companyId){
        return companyRepository.getCompanyById(companyId)
                .orElseThrow(() -> new RuntimeException("Company Not Found" + " , " +  companyId));
    }

    private JobPost getJobPostDetailsById(Long postId){
        return jobPostRepository.getJobPostById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Post not found" + " , " + postId));
    }

    private User getUserDetailsById(Long userId){
        return userRepository.userFindById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found" + " , " + userId));
    }

    /* ----- PUBLIC METHODS ----- */
}

