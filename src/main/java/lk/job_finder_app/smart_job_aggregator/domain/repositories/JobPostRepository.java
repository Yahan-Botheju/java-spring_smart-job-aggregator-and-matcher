package lk.job_finder_app.smart_job_aggregator.domain.repositories;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;

import java.util.List;

public interface JobPostRepository {
    //get all posts
    List<JobPost> getAllJobPosts();


}
