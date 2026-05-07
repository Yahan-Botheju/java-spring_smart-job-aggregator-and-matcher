package lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobApplication;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.globalExceptionHandler.superClasses.ResourceNotFoundException;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.entity.JobApplicationEntity;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.jpa.JpaJobApplicationRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.mapper.JobApplicationPersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JobApplicationRepositoryImpl implements JobApplicationRepository {

    //inject job application jpa
    private final JpaJobApplicationRepository jpaJobApplicationRepository;

    //inject persistence mapper
    private final JobApplicationPersistenceMapper jobApplicationPersistenceMapper;

    /* HELPER METHODS */
    @Override
    public  Optional<JobApplication> jobApplicationFindById(long jobApplicationId){
        return jpaJobApplicationRepository.findById(jobApplicationId)
                .map(jobApplicationPersistenceMapper::toDomainModel);

    }
    //get all job applications
    @Override
    public List<JobApplication> getAllJobApplications(){
        return jpaJobApplicationRepository.findAll().stream()
                .map(jobApplicationPersistenceMapper::toDomainModel).toList();
    }

    //create job application
    @Override
    public JobApplication applyForJob(JobApplication jobApplication){
        JobApplicationEntity jobApplicationEntity = jobApplicationPersistenceMapper.toEntity(jobApplication);
        JobApplicationEntity savedJobApplicationEntity = jpaJobApplicationRepository.save(jobApplicationEntity);
        return jobApplicationPersistenceMapper.toDomainModel(savedJobApplicationEntity);
    }

    //update job application
    @Override
    public JobApplication updateJobApplication(
            Long jobApplicationId,
            JobApplication jobApplication
    ){
        JobApplicationEntity existingEntity = jpaJobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Application Not Found" + " , " +  jobApplicationId));
        JobApplicationEntity updatedEntity = jobApplicationPersistenceMapper.toUpdateEntity(jobApplication, existingEntity);
        JobApplicationEntity savedEntity = jpaJobApplicationRepository.save(updatedEntity);
        return jobApplicationPersistenceMapper.toDomainModel(savedEntity);
    }
}
