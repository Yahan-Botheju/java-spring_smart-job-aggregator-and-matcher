package lk.job_finder_app.smart_job_aggregator.web.user.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long  userId;
    private String userName;
    private String userEmail;
}
