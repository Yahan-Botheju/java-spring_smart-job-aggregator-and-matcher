package lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.mapper;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobApplication;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.entity.JobApplicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobApplicationPersistenceMapper {

    //domain model to entity
    JobApplicationEntity toEntity(JobApplication jobApplication);

    //entity to domain model
    JobApplication toDomainModel(JobApplicationEntity jobApplicationEntity);

    //update
    JobApplicationEntity toUpdateEntity(
            JobApplication jobApplication,
            @MappingTarget
            JobApplicationEntity jobApplicationEntity
    );
}
