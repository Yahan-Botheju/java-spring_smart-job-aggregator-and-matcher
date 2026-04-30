package lk.job_finder_app.smart_job_aggregator.infrastructure.user.config;

import lk.job_finder_app.smart_job_aggregator.domain.models.User;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.UserRepositoryImpl;
import lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.mapper.UserPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserPersistenceBeanConfig {
    @Bean
    UserRepository userRepository(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        return new UserRepositoryImpl(jpaUserRepository, userPersistenceMapper);
    }
}
