package lk.job_finder_app.smart_job_aggregator.usecase.jobPost;

import lk.job_finder_app.smart_job_aggregator.domain.models.Company;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobPostWithCompany;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class JobPostUseCaseImpl implements JobPostUseCase{

    //inject job post repo
    private final JobPostRepository jobPostRepository;

    //inject company repo
    private final CompanyRepository companyRepository;

    /* ----- HELPER METHODS ----- */

    //method for getting company details
    private Company getCompanyDetailsById(Long companyId){
        return companyRepository.getCompanyById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company Not Found"));
    }






    /* ----- PUBLIC METHODS ----- */


    //get all job posts
    @Override
    public List<JobPostWithCompany> getAllJobPosts(){

        return  jobPostRepository.getAllJobPosts().stream().map(jobPost -> {
            Company company = getCompanyDetailsById(jobPost.getCompanyId());
            return new JobPostWithCompany(jobPost,company);
        }).toList();
    }

    //create job post
    public JobPostWithCompany createJobPost(JobPost jobPost){
        //check company availability
        jobPostRepository.getJobPostById(jobPost.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Not Found"));
        //set default job status using domain model method
        jobPost.setDefaultJobStatus();
        //save new job post in db
        JobPost savedJobPost = jobPostRepository.createJobPost(jobPost);
        //match job post with related company
        Company company = getCompanyDetailsById(savedJobPost.getCompanyId());
        //return both results for response
        return new JobPostWithCompany(savedJobPost, company);

    }
}
