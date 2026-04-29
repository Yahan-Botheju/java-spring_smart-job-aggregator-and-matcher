package lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.jpa;

import lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
