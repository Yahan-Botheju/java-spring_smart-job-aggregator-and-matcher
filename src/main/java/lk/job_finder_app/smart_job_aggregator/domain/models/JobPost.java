package lk.job_finder_app.smart_job_aggregator.domain.models;

import lk.job_finder_app.smart_job_aggregator.domain.models.enums.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPost {
    private Long postId;
    private String postTitle;
    private String postDescription;
    private BigDecimal postSalary;
    private JobStatus jobStatus;
    private Set<String> skillsRequired;
}
