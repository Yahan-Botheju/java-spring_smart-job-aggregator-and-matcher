package lk.job_finder_app.smart_job_aggregator.usecase.jobApplication;

import lk.job_finder_app.smart_job_aggregator.domain.models.*;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    //get all job applications
    @Override
    public List<JobApplicationAggregate> getAllJobApplications(){
        return jobApplicationRepository.getAllJobApplications().stream()
                .map(jobApplication -> {
                    Company company = getCompanyDetailsById(jobApplication.getCompanyId());
                    JobPost jobPost = getJobPostDetailsById(jobApplication.getJobPostId());
                    User user = getUserDetailsById(jobApplication.getUserId());

                    return new JobApplicationAggregate(company,jobApplication, jobPost, user);
                }).toList();
    }

    //create job application
    @Override
    public JobApplicationAggregate createJobApplication(JobApplication jobApplication){
        //validate using helper methods (JOB-POST, COMPANY, USER)
        JobPost jobPost = getJobPostDetailsById(jobApplication.getJobPostId());
        Company company = getCompanyDetailsById(jobPost.getCompanyId());
        User user = getUserDetailsById(jobApplication.getUserId());

        jobApplication.setCompanyId(jobPost.getCompanyId());

        //set date and time, default status
        jobApplication.setAppliedAt(LocalDateTime.now());
        jobApplication.jobApplicationDefaultStatus();

        JobApplication savedJobApplication = jobApplicationRepository.createJobApplication(jobApplication);

        return new JobApplicationAggregate(company,savedJobApplication, jobPost, user);
    }

    //update job application
    @Override
    public JobApplicationAggregate updateJobApplication(
            Long jobApplicationId,
            JobApplication jobApplication
    ){
        jobApplicationRepository.jobApplicationFindById(jobApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Application Not Found" + " , " + jobApplicationId));

        JobApplication savedJobApplication = jobApplicationRepository.updateJobApplication(jobApplicationId, jobApplication);

        JobPost jobPost = getJobPostDetailsById(savedJobApplication.getJobPostId());
        Company company = getCompanyDetailsById(savedJobApplication.getCompanyId());
        User user = getUserDetailsById(savedJobApplication.getUserId());

        return new JobApplicationAggregate(company,jobApplication, jobPost, user);
    }

}

