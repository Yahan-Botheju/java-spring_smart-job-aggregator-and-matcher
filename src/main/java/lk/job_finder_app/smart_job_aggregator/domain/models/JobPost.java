package lk.job_finder_app.smart_job_aggregator.domain.models;

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
    private String title;
    private String description;
    private BigDecimal salary;
    private Set<String> skillsRequired;
}
