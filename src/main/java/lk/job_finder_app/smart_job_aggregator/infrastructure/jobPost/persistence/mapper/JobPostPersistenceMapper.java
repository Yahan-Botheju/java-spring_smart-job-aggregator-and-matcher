package lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.mapper;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.entity.JobPostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobPostPersistenceMapper {

    //domain model to entity
    JobPostEntity toEntity(JobPost jobPost);

    //entity to domain model
    JobPost toDomainModel(JobPostEntity jobPostEntity);

    //set new data to existing entity
    JobPostEntity updateEntityWithNewData(JobPost jobPost, @MappingTarget JobPostEntity jobPostEntity);
}
