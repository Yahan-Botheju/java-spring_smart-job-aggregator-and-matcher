package lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence;

import aQute.bnd.annotation.headers.Category;
import lk.job_finder_app.smart_job_aggregator.domain.models.User;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.entity.UserEntity;
import lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.job_finder_app.smart_job_aggregator.infrastructure.user.persistence.mapper.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    //inject user jpa repo
    private final JpaUserRepository jpaUserRepository;

    //inject user persistence mapper
    private final UserPersistenceMapper userPersistenceMapper;


    //get user by ID (CUSTOM METHOD FOR VALIDATION)
    @Override
    public Optional<User> userFindById(Long userId){
        return jpaUserRepository.findById(userId).map(userPersistenceMapper::toDomainModel);
    }

    //get all users
    @Override
    public List<User> getAllUsers(){
        List<UserEntity>  userEntities = jpaUserRepository.findAll();
        return userEntities.stream().map(userPersistenceMapper::toDomainModel).toList();
    }
}
