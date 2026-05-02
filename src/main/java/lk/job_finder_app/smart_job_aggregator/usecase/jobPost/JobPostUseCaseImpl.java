package lk.job_finder_app.smart_job_aggregator.usecase.jobPost;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JobPostUseCaseImpl implements JobPostUseCase{

    //inject job post repo
    private final JobPostRepository jobPostRepository;

    //get all job posts
    @Override
    public List<JobPost> getAllJobPosts(){
        return  jobPostRepository.getAllJobPosts();
    }

    //create job post
    public JobPost createJobPost(JobPost jobPost){
        return jobPostRepository.createJobPost(jobPost);
    }
}
