package lk.job_finder_app.smart_job_aggregator.web.user.controllers;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPostWithCompanyAggregate;
import lk.job_finder_app.smart_job_aggregator.domain.models.User;
import lk.job_finder_app.smart_job_aggregator.domain.models.enums.RoleName;
import lk.job_finder_app.smart_job_aggregator.globalResponseHandler.StandardResponse;
import lk.job_finder_app.smart_job_aggregator.usecase.jobPost.JobPostUseCase;
import lk.job_finder_app.smart_job_aggregator.usecase.user.UserUseCase;
import lk.job_finder_app.smart_job_aggregator.web.jobPost.DTOs.JobPostResponseDTO;
import lk.job_finder_app.smart_job_aggregator.web.jobPost.webMappers.JobPostWebMapper;
import lk.job_finder_app.smart_job_aggregator.web.security.Authorize;
import lk.job_finder_app.smart_job_aggregator.web.user.DTOs.UserRequestDTO;
import lk.job_finder_app.smart_job_aggregator.web.user.DTOs.UserResponseDTO;
import lk.job_finder_app.smart_job_aggregator.web.user.webMappers.UserWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    //inject user usecase
    private final UserUseCase userUseCase;

    //inject user web mapper
    private final UserWebMapper userWebMapper;

    //inject job-post usecase
    private final JobPostUseCase jobPostUseCase;

    //inject job-post web mapper
    private final JobPostWebMapper jobPostWebMapper;

    //get all users
    @GetMapping("/")
    @Authorize(RoleName.ADMIN)
    public ResponseEntity<StandardResponse<List<UserResponseDTO>>> getAllUsers(){
        List<User> usersDomainModel = userUseCase.getAllUsers();

        List<UserResponseDTO> toResponseDTO = usersDomainModel.stream().map(userWebMapper::toResponseDTO).toList();

        return ResponseEntity.ok(
                new StandardResponse<>(
                        200,
                        "users data fetched successfully",
                        LocalDateTime.now(),
                        toResponseDTO
                )
        );

    }
    //create user
    @PostMapping("/")
    @Authorize({RoleName.USER,  RoleName.ADMIN})
    public ResponseEntity<StandardResponse<UserResponseDTO>> createUser(
            @RequestBody UserRequestDTO userRequestDTO
    ){
        //turn to domain model
        User toDomainModel = userWebMapper.toDomainModel(userRequestDTO);

        //set to usecase and get response
        User responseModel = userUseCase.createUser(toDomainModel);

        //turn to response
        UserResponseDTO toResponseDTO = userWebMapper.toResponseDTO(responseModel);

        return  ResponseEntity.created(URI.create("/api/v1/jobsapplicator/users/" + "," + toResponseDTO.getUserId())).body(
                new StandardResponse<>(
                        201,
                        "user created successfully",
                        LocalDateTime.now(),
                        toResponseDTO
                )
        );

    }
    //update user
    @PutMapping("/{userId}")
    @Authorize({RoleName.USER, RoleName.ADMIN})
    public ResponseEntity<StandardResponse<UserResponseDTO>> updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequestDTO userRequestDTO
    ){
        User toDomainModel = userWebMapper.toDomainModel(userRequestDTO);
        User responseModel = userUseCase.updateUser(userId, toDomainModel);
        UserResponseDTO toResponseDTO = userWebMapper.toResponseDTO(responseModel);

        return  ResponseEntity.ok(
                new StandardResponse<>(
                        200,
                        "user updated successfully",
                        LocalDateTime.now(),
                        toResponseDTO
                )
        );

    }
    //delete user
    @DeleteMapping("/{userId}")
    @Authorize(RoleName.ADMIN)
    public ResponseEntity<String> deleteUser(
            @PathVariable Long userId
    ){
        userUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    //use job post matching method
    @GetMapping("/recommendations/{userId}")
    @Authorize({RoleName.USER, RoleName.ADMIN})
    public ResponseEntity<StandardResponse<List<JobPostResponseDTO>>> getRecommendedJobPostsForUser(
            @PathVariable Long userId
    ){
        List<JobPostWithCompanyAggregate> jobPosts = jobPostUseCase.getRecommendedJobsForUser(userId);
        List<JobPostResponseDTO> responseDTO = jobPosts.stream().map(jobPostWebMapper::customResponseDTO).toList();

        return ResponseEntity.ok(new StandardResponse<>(
                200,
                "Details fetched successfully",
                LocalDateTime.now(),
                responseDTO
        ));
    }

    //get multisource data related to use skills
    @GetMapping("/recommendations/multi-source/{userId}")
    @Authorize({RoleName.USER,  RoleName.ADMIN})
    public ResponseEntity<StandardResponse<List<JobPostResponseDTO>>> getMultiSourceRecommendations(
            @PathVariable Long userId
    ){
        List<JobPostWithCompanyAggregate> threadJobPost = jobPostUseCase.getMultiSourceRecommendations(userId);
        List<JobPostResponseDTO> responseDTO = threadJobPost.stream().map(jobPostWebMapper::customResponseDTO).toList();

        return ResponseEntity.ok(new StandardResponse<>(
                200,
                "Details fetched successfully",
                LocalDateTime.now(),
                responseDTO
        ));


    }

}
