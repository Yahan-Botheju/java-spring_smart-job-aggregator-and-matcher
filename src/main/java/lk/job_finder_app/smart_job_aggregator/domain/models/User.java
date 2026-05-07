package lk.job_finder_app.smart_job_aggregator.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String userName;
    private String userEmail;

    private Role role;

    private Set<String> skillsRequired;
}
