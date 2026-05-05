package lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobApplication;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.JobApplicationRepository;
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
}
