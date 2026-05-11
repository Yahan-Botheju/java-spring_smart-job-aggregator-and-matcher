package lk.job_finder_app.smart_job_aggregator.infrastructure.external_api.museAPI.mappers;

import lk.job_finder_app.smart_job_aggregator.domain.models.JobPost;
import lk.job_finder_app.smart_job_aggregator.domain.models.enums.JobStatus;
import lk.job_finder_app.smart_job_aggregator.infrastructure.external_api.museAPI.DTOs.ExternalJobResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

@Component
public class ExternalJobMapper {

    //map each taken data into initiate fields of the system
    public JobPost toDomain(
            ExternalJobResponseDTO.TheMuseJob theMuseJob
    ) {
        JobPost jobPost = new JobPost();

        jobPost.setPostId(-1L);
        jobPost.setPostTitle(theMuseJob.name());

        String removeTags = theMuseJob.contents().replaceAll("<[^>]*>", "");
        jobPost.setPostDescription(removeTags);

        jobPost.setJobStatus(JobStatus.ACTIVE);
        jobPost.setCreatedAt(LocalDate.now());
        jobPost.setSkillsRequired(new HashSet<>());
        jobPost.setPostSalary(BigDecimal.ZERO);
        jobPost.setCompanyId(0L);

        return jobPost;
    }
}
