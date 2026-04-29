package lk.job_finder_app.smart_job_aggregator.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplication {
    private Long jobApplicationId;
    private Long userId;
    private Long jobPostId;
    private String resumeUrl;
    private double matchScore;
    private LocalDateTime appliedAt;
}
