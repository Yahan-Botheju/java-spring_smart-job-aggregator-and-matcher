package lk.job_finder_app.smart_job_aggregator.web.jobPost.controllers;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.globalResponseHandler.StandardResponse;
import lk.job_finder_app.smart_job_aggregator.usecase.jobPost.JobPostUseCase;
import lk.job_finder_app.smart_job_aggregator.web.jobPost.DTOs.JobPostResponseDTO;
import lk.job_finder_app.smart_job_aggregator.web.jobPost.webMappers.JobPostWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<JobPost> jobPosts = jobPostUseCase.getAllJobPosts();

        List<JobPostResponseDTO> responseDTO = jobPosts.stream().map(jobPostWebMapper::toResponseDTO).toList();

        return ResponseEntity.ok(new StandardResponse<>(
                200,
                "Details fetched successfully",
                LocalDateTime.now(),
                responseDTO
        ));
    }

}
