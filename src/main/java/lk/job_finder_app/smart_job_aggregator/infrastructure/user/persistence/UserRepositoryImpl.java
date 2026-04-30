package lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.mapper.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    //inject user jpa repo
    private final JpaUserRepository jpaUserRepository;

    //inject user persistence mapper
    private final UserPersistenceMapper userPersistenceMapper;
}
