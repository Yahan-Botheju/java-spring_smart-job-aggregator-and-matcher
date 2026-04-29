package lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.jpa;

import lk.job_finder_app.smart_job_aggregator.infrastructure.jobApplication.persistence.entity.JobApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJobApplicationRepository extends JpaRepository<JobApplicationEntity, Long> {
}
