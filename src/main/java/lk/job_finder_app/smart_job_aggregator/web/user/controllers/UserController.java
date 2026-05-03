package lk.job_finder_app.smart_job_aggregator.web.user.controllers;

import lk.job_finder_app.smart_job_aggregator.domain.models.User;
import lk.job_finder_app.smart_job_aggregator.globalResponseHandler.StandardResponse;
import lk.job_finder_app.smart_job_aggregator.usecase.user.UserUseCase;
import lk.job_finder_app.smart_job_aggregator.web.user.DTOs.UserResponseDTO;
import lk.job_finder_app.smart_job_aggregator.web.user.webMappers.UserWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jobsapplicator/users/")
@RequiredArgsConstructor
public class UserController {

    //inject user usecase
    private final UserUseCase userUseCase;

    //inject user web mapper
    private final UserWebMapper userWebMapper;

    //get all users
    @GetMapping("/")
    public ResponseEntity<StandardResponse<List<UserResponseDTO>>> getAllUsers(){
        List<User> usersDomainModel = userUseCase.getAllUsers();

        List<UserResponseDTO> toResponseDTO = usersDomainModel.stream().map(userWebMapper::toResponseDTO).toList();

        return ResponseEntity.ok( new StandardResponse<>(
                200,
                "users data featched successfully",
                LocalDateTime.now(),
                toResponseDTO
        ));

    }
}
