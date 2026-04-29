package lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.jpa;

import lk.job_finder_app.smart_job_aggregator.infrastructure.jobPost.persistence.entity.JobPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJobPostRepository extends JpaRepository<JobPostEntity, Long> {
}
