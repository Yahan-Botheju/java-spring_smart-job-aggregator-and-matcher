package lk.job_finder_app.smart_job_aggregator.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private Long companyId;
    private String companyName;
    private String companyIndustry;
    private BigDecimal companyRating;
}
