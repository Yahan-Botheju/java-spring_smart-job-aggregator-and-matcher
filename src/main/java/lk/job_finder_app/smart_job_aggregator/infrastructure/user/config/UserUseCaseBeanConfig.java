package lk.job_finder_app.smart_job_aggregator.infrastructure.user.config;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lk.job_finder_app.smart_job_aggregator.usecase.user.UserUseCase;
import lk.job_finder_app.smart_job_aggregator.usecase.user.UserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserUseCaseBeanConfig {
    @Bean
    UserUseCase userUseCase(
            UserRepository userRepository
    ) {
        return new UserUseCaseImpl(userRepository);
    }
}
