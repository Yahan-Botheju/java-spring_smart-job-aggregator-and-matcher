package lk.job_finder_app.smart_job_aggregator.web.jobApplication.controllers;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobApplication;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobApplicationAggregate;
import lk.job_finder_app.smart_job_aggregator.globalResponseHandler.StandardResponse;
import lk.job_finder_app.smart_job_aggregator.usecase.jobApplication.JobApplicationUseCase;
import lk.job_finder_app.smart_job_aggregator.web.jobApplication.DTOs.JobApplicationRequestDTO;
import lk.job_finder_app.smart_job_aggregator.web.jobApplication.DTOs.JobApplicationResponseDTO;
import lk.job_finder_app.smart_job_aggregator.web.jobApplication.webMappers.JobApplicationWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jobsapplicator/jobapplications")
@RequiredArgsConstructor
public class JobApplicationController {

    //inject web mapper
    private final JobApplicationWebMapper  jobApplicationWebMapper;

    //inject use case
    private final JobApplicationUseCase jobApplicationUseCase;


    //get all job applications
    @GetMapping("/")
    public ResponseEntity<StandardResponse<List<JobApplicationResponseDTO>>> getAllJobApplications(){
        List<JobApplicationAggregate> jobApplications = jobApplicationUseCase.getAllJobApplications();

        List<JobApplicationResponseDTO> responseDTOS = jobApplications.stream()
                .map(jobApplicationWebMapper::aggregatorResponseDTO).toList();

        return ResponseEntity.ok(new StandardResponse<>(
                200,
                "Details fetched successfully",
                LocalDateTime.now(),
                responseDTOS
        ));
    }

    //create job application
    @PostMapping("/")
    public ResponseEntity<StandardResponse<JobApplicationResponseDTO>> createJobApplication(
            @RequestBody JobApplicationRequestDTO jobApplicationRequestDTO
    ){
        JobApplication toDomainModel = jobApplicationWebMapper.toDomainModel(jobApplicationRequestDTO);
        JobApplicationAggregate jobApplicationAggregate = jobApplicationUseCase.createJobApplication(toDomainModel);
        JobApplicationResponseDTO responseDTO = jobApplicationWebMapper.aggregatorResponseDTO(jobApplicationAggregate);

        return ResponseEntity.created(URI.create("/api/v1/jobsapplicator/jobapplications/" + responseDTO.getJobApplicationId()))
                .body(new StandardResponse<>(
                        201,
                        "Job application created",
                        LocalDateTime.now(),
                        responseDTO
                ));
    }
}
