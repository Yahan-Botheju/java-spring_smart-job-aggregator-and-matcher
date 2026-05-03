package lk.job_finder_app.smart_job_aggregator.usecase.user;

import lk.job_finder_app.smart_job_aggregator.domain.models.User;
import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase{

    //inject user repo
    private final UserRepository userRepository;

    //get all users
    @Override
    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }
}
