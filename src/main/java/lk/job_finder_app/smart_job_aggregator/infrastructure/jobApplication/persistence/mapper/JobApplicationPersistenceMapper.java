package lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.mapper;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobApplication;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.entity.JobApplicationEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobApplicationPersistenceMapper {

    //domain model to entity
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "jobPostId", target = "jobPost.jobPostId")
    JobApplicationEntity toEntity(JobApplication jobApplication);

    //entity to domain model
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "jobPost.jobPostId", target = "jobPostId")
    JobApplication toDomainModel(JobApplicationEntity jobApplicationEntity);

    //update
    JobApplicationEntity toUpdateEntity(
            JobApplication jobApplication,
            @MappingTarget
            JobApplicationEntity jobApplicationEntity
    );
}
