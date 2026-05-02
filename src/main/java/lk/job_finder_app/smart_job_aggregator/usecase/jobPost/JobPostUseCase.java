package lk.job_finder_app.smart_job_aggregator.usecase.jobPost;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobPostWithCompany;

import java.util.List;

public interface JobPostUseCase {
    //get all job posts
    List<JobPost> getAllJobPosts();

    //create job post
    JobPostWithCompany createJobPost(JobPost jobPost);
}
