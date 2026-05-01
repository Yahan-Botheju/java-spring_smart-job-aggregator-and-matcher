package lk.job_finder_app.smart_job_aggregator.web.jobPost.webMappers;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.web.jobPost.DTOs.JobPostRequestDTO;
import lk.job_finder_app.smart_job_aggregator.web.jobPost.DTOs.JobPostResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobPostWebMapper {

    //domain model to responseDTO
    JobPostResponseDTO toResponseDTO(JobPost jobPost);

    //requestDTO to domain model
    JobPost toDomainModel(JobPostRequestDTO jobPostRequestDTO);
}
