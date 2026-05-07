package lk.job_finder_app.smart_job_aggregator.web.user.controllers;

import lk.job_finder_app.smart_job_aggregator.domain.models.User;
import lk.job_finder_app.smart_job_aggregator.globalResponseHandler.StandardResponse;
import lk.job_finder_app.smart_job_aggregator.usecase.user.UserUseCase;
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

    //get all users
    @GetMapping("/")
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
    public ResponseEntity<String> deleteUser(
            @PathVariable Long userId
    ){
        userUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
