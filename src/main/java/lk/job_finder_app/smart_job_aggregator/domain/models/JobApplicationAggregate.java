package lk.job_finder_app.smart_job_aggregator.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationAggregate {
    private Company company;
    private JobApplication jobApplication;
    private JobPost jobPost;
    private User user;
}
