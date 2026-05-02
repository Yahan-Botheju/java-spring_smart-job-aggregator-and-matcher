package lk.job_finder_app.smart_job_aggregator.web.jobPost.controllers;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.domain.models.JobPostWithCompany;
import lk.job_finder_app.smart_job_aggregator.globalResponseHandler.StandardResponse;
import lk.job_finder_app.smart_job_aggregator.usecase.jobPost.JobPostUseCase;
import lk.job_finder_app.smart_job_aggregator.web.jobPost.DTOs.JobPostRequestDTO;
import lk.job_finder_app.smart_job_aggregator.web.jobPost.DTOs.JobPostResponseDTO;
import lk.job_finder_app.smart_job_aggregator.web.jobPost.webMappers.JobPostWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/jobsapplicator/jobpost/")
public class JobPostController {

    //inject use case
    private final JobPostUseCase  jobPostUseCase;

    //inject web mapper
    private final JobPostWebMapper jobPostWebMapper;

    //get all job posts
    @GetMapping("/")
    public ResponseEntity<StandardResponse<List<JobPostResponseDTO>>> getAllJobPosts() {
        //get all job posts as domain model list
        List<JobPostWithCompany> jobPosts = jobPostUseCase.getAllJobPosts();

        List<JobPostResponseDTO> responseDTO = jobPosts.stream().map(jobPostWebMapper::customerResponseDTO).toList();

        return ResponseEntity.ok(new StandardResponse<>(
                200,
                "Details fetched successfully",
                LocalDateTime.now(),
                responseDTO
        ));
    }

    //create new post
    @PostMapping("/")
    public ResponseEntity<StandardResponse<JobPostResponseDTO>> createJobPost(
            @RequestBody JobPostRequestDTO jobPostRequestDTO
            ){
        //turn requestDTO to domain model
        JobPost toDomainModel = jobPostWebMapper.toDomainModel(jobPostRequestDTO);
        //set values to usecase (custom job post and company method)
        JobPostWithCompany savedJobPost = jobPostUseCase.createJobPost(toDomainModel);
        //get response with company name id using custom mapper
        JobPostResponseDTO responseDTO = jobPostWebMapper.customerResponseDTO(savedJobPost);

        return ResponseEntity.created(URI.create("/api/v1/jobsapplicator/jobpost/"))
                .body(new StandardResponse<>(
                        201,
                        "Job Post created successfully" + " , " +  responseDTO.getPostId(),
                        LocalDateTime.now(),
                        responseDTO
                ));

    }

}
