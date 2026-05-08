package lk.job_finder_app.smart_job_aggregator.usecase.jobPost;

import lk.job_finder_app.smart_job_aggregator.domain.models.Company;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobPostWithCompanyAggregate;
import lk.job_finder_app.smart_job_aggregator.domain.models.User;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class JobPostUseCaseImpl implements JobPostUseCase{

    //inject job post repo
    private final JobPostRepository jobPostRepository;

    //inject company repo
    private final CompanyRepository companyRepository;

    //inject user repo
    private final UserRepository userRepository;

    /* ----- HELPER METHODS ----- */

    //method for getting company details
    private Company getCompanyDetailsById(Long companyId){
        return companyRepository.getCompanyById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company Not Found"));
    }

    //set corn job
    @Scheduled(cron = "0 * * * * *")
    public void executeJobPostExpire(){
        LocalDate expiryLimit = LocalDate.now().minusDays(1);
        jobPostRepository.expireOldJobPosts(expiryLimit);
    }

    public List<JobPost> getRecommendedJobsForUser(Long userId){

    }



    /* ----- PUBLIC METHODS ----- */


    //get all job posts
    @Override
    public List<JobPostWithCompanyAggregate> getAllJobPosts(){

        return  jobPostRepository.getAllJobPosts().stream().map(jobPost -> {
            Company company = getCompanyDetailsById(jobPost.getCompanyId());
            return new JobPostWithCompanyAggregate(jobPost,company);
        }).toList();
    }

    //create job post
    @Override
    public JobPostWithCompanyAggregate createJobPost(JobPost jobPost){
        //check company availability using helper method
        Company company = getCompanyDetailsById(jobPost.getCompanyId());

        //set default job status using domain model method
        jobPost.setDefaultJobStatus();

        //set createdAt
        jobPost.setCreatedAt(LocalDate.now());

        //save new job post in db
        JobPost savedJobPost = jobPostRepository.createJobPost(jobPost);

        //return both results for response
        return new JobPostWithCompanyAggregate(savedJobPost, company);

    }

    //update job post
    @Override
    public JobPostWithCompanyAggregate updateJobPost(
            Long postId,
            JobPost jobPost
    ){
       jobPostRepository.getJobPostById(postId)
               .orElseThrow(() -> new ResourceNotFoundException("Job Post Not Found" + ", " +  postId));

       jobPost.setDefaultJobStatus();
       //set update domain model to repo for db
       JobPost savedJobPost = jobPostRepository.updateJobPost(postId, jobPost);
       Company company = getCompanyDetailsById(savedJobPost.getCompanyId());

       return new JobPostWithCompanyAggregate(savedJobPost, company);
    }

    //delete job post
    @Override
    public void deleteJobPost(Long postId){
        jobPostRepository.getJobPostById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Post Not Found" + ", " +  postId));

        jobPostRepository.deleteJobPost(postId);
    }
}
