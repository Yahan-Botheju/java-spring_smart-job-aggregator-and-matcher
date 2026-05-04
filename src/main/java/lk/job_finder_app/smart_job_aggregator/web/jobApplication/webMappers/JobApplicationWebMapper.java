package lk.job_finder_app.smart_job_aggregator.web.jobApplication.webMappers;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobApplication;
import lk.job_finder_app.smart_job_aggregator.web.jobApplication.DTOs.JobApplicationRequestDTO;
import lk.job_finder_app.smart_job_aggregator.web.jobApplication.DTOs.JobApplicationResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobApplicationWebMapper {

    //domain model to response
    JobApplicationResponseDTO toResponseDTO(JobApplication jobApplication);

    //request to domain model
    JobApplication toDomainModel(JobApplicationRequestDTO jobApplicationRequestDTO);
}
