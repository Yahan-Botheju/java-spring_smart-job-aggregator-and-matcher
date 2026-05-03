package lk.job_finder_app.smart_job_aggregator.domain.repositories;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;

import java.util.List;
import java.util.Optional;

public interface JobPostRepository {

    //job post find by id (CUSTOM METHOD)
    Optional<JobPost> getJobPostById(Long id);

    //get all posts
    List<JobPost> getAllJobPosts();

    //create job post
    JobPost createJobPost(JobPost jobPost);

    //update job post
    JobPost updateJobPost(Long postId, JobPost jobPost);

    //delete job post
    void deleteJobPost(Long id);

}
