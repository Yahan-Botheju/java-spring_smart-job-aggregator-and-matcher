package lk.job_finder_app.smart_job_aggregator.web.jobApplication.DTOs;

import lk.job_finder_app.smart_job_aggregator.domain.models.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationResponseDTO {
    //application
    private Long jobApplicationId;
    private double matchScore;
    private ApplicationStatus applicationStatus;
    private LocalDateTime appliedAt;
    private String resumeUrl;

    //user
    private String userName;

    //job post
    private String postTitle;

    //company
    private String companyName;





}
