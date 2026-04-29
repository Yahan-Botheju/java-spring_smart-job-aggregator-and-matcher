package lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence.jpa;

import lk.job_finder_app.smart_job_aggregator.infrastructure.company.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
