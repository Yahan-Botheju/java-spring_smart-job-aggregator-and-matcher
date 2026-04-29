package lk.job_finder_app.smart_job_aggregator.domain.models;

import lk.job_finder_app.smart_job_aggregator.domain.models.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplication {
    private Long jobApplicationId;
    private String resumeUrl;
    private double matchScore;
    private ApplicationStatus applicationStatus;
    private LocalDateTime appliedAt;

    private Long userId;
    private Long jobPostId;
}
