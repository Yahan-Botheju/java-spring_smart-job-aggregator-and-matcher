package lk.job_finder_app.smart_job_aggregator.usecase.user;

import lk.job_finder_app.smart_job_aggregator.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase{

    //inject user repo
    private final UserRepository userRepository;
}
