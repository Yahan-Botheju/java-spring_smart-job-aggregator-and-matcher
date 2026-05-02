package lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobPostRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.entity.JobPostEntity;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.jpa.JpaJobPostRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.mapper.JobPostPersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JobPostRepositoryImpl implements JobPostRepository {

    //inject jpa repo
    private final JpaJobPostRepository jpaJobPostRepository;

    //inject persistence mapper
    private final JobPostPersistenceMapper jobPostPersistenceMapper;

    //job post find by id (CUSTOM METHOD)
    public Optional<JobPost> getJobPostById(long postId){
        return jpaJobPostRepository.findById(postId).map(jobPostPersistenceMapper::toDomainModel);
    }

    //get all job posts
    @Override
    public List<JobPost> getAllJobPosts(){
        List<JobPostEntity> jobPosts = jpaJobPostRepository.findAll();

        return jobPosts.stream().map(jobPostPersistenceMapper::toDomainModel).toList();
    }

    //create job post
    @Override
    public JobPost createJobPost(JobPost jobPost){
        //call default value set method in domain model
        jobPost.setDefaultJobStatus();

        //set to entity
        JobPostEntity jobPostEntity = jobPostPersistenceMapper.toEntity(jobPost);
        
        //save in db and take save values
        JobPostEntity savedJobPostEntity = jpaJobPostRepository.save(jobPostEntity);
        //turn to domain model and return
        return jobPostPersistenceMapper.toDomainModel(savedJobPostEntity);
    }
}
