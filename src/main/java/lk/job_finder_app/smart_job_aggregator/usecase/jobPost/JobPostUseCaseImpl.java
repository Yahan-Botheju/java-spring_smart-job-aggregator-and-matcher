package lk.job_finder_app.smart_job_aggregator.usecase.jobPost;

import lk.job_finder_app.smart_job_aggregator.domain.models.Company;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobPostWithCompanyAggregate;
import lk.job_finder_app.smart_job_aggregator.domain.models.User;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.CompanyRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.external_api.museAPI.DTOs.ExternalJobResponseDTO;
import lk.job_finder_app.smart_job_aggregator.infrastructure.external_api.museAPI.client.TheMuseClient;
import lk.job_finder_app.smart_job_aggregator.infrastructure.external_api.museAPI.mappers.ExternalJobMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class JobPostUseCaseImpl implements JobPostUseCase{

    //inject job post repo
    private final JobPostRepository jobPostRepository;

    //inject company repo
    private final CompanyRepository companyRepository;

    //inject user repo
    private final UserRepository userRepository;

    //inject muse client
    private final TheMuseClient theMuseClient;

    //inject external job mapper
    private final ExternalJobMapper externalJobMapper;


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

    //job matching related to user
    @Override
    public List<JobPostWithCompanyAggregate> getRecommendedJobsForUser(Long userId){
        //check user availability
        User user = userRepository.userFindById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        //get skills as set of user
        Set<String> userSkills = user.getSkillsRequired();

        //check user skills
        if (userSkills == null || userSkills.isEmpty()){
            return List.of();
        }

        //get jobs that matches to user
        List<JobPost> matchedJobs = jobPostRepository.findJobsByMatchingSkills(userSkills);

        //set related details and return
        return  matchedJobs.stream().map(jobPosts -> {
            Company company = getCompanyDetailsById(jobPosts.getCompanyId());
            return new JobPostWithCompanyAggregate(jobPosts, company);
        }).toList();

    }

    public List<JobPostWithCompanyAggregate> getMultiSourceRecommendations(
            Long userId
    ){
        //allocate the data that taken from local db
        CompletableFuture<List<JobPostWithCompanyAggregate>>
                localJobsFuture = CompletableFuture.supplyAsync(() -> {
                    return getRecommendedJobsForUser(userId);
        });

        CompletableFuture<List<JobPostWithCompanyAggregate>>
                externalJobsFuture = CompletableFuture.supplyAsync(() -> {
            List<ExternalJobResponseDTO.TheMuseJob> museJobs = theMuseClient.fetchExternalJobs();

            return museJobs.stream().map( theMuseJob -> {
                JobPost posts = ex
            })
        })
    }


}
