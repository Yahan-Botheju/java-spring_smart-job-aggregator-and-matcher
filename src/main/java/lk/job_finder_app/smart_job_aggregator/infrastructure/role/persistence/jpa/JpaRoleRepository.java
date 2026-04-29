package lk.job_finder_app.smart_job_aggregator.infrastructure.role.persistence.jpa;

import lk.job_finder_app.smart_job_aggregator.infrastructure.role.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
}
