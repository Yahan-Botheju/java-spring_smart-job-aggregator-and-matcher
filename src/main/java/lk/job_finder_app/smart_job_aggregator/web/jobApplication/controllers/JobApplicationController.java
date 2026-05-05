package lk.job_finder_app.smart_job_aggregator.web.jobApplication.controllers;

import lk.job_finder_app.smart_job_aggregator.usecase.jobApplication.JobApplicationUseCase;
import lk.job_finder_app.smart_job_aggregator.web.jobApplication.webMappers.JobApplicationWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobsapplicator/jobapplications")
@RequiredArgsConstructor
public class JobApplicationController {

    //inject web mapper
    private final JobApplicationWebMapper  jobApplicationWebMapper;

    //inject use case
    private final JobApplicationUseCase jobApplicationUseCase;
}
