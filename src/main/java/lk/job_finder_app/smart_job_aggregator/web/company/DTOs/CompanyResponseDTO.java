package lk.job_finder_app.smart_job_aggregator.web.company.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDTO {
    private Long companyId;
    private String companyName;
    private String companyIndustry;
    private BigDecimal companyRating;
}
