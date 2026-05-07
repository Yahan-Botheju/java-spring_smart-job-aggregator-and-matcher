package lk.job_finder_app.smart_job_aggregator.domain.models;

import lk.job_finder_app.smart_job_aggregator.domain.models.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

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
    private Long companyId;

    //job application default status
    public void jobApplicationDefaultStatus() {
        if (this.applicationStatus == null) {
            this.applicationStatus = ApplicationStatus.PENDING;
        }
    }

    //create method for calculate matching score
    public void calculateMatchScore(
            Set<String> userSkills,
            Set<String> requiredJobSkills
    ) {
        if(userSkills == null || userSkills.isEmpty() ||requiredJobSkills == null || requiredJobSkills.isEmpty()) {
            this.matchScore = 0.0;
            return;
        }
        long matchCount = userSkills.stream().filter(requiredJobSkills::contains).count();
        this.matchScore = (((double)  matchCount) /  requiredJobSkills.size()) * 100;
    }
}
