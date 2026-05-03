package lk.job_finder_app.smart_job_aggregator.web.user.controllers;

import lk.job_finder_app.smart_job_aggregator.usecase.user.UserUseCase;
import lk.job_finder_app.smart_job_aggregator.web.user.webMappers.UserWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobsapplicator/users/")
@RequiredArgsConstructor
public class UserController {

    //inject user usecase
    private final UserUseCase userUseCase;

    //inject user web mapper
    private final UserWebMapper userWebMapper;
}
