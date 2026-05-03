package lk.job_finder_app.smart_job_aggregator.usecase.user;

import lk.job_finder_app.smart_job_aggregator.domain.models.User;

import java.util.List;

public interface UserUseCase {

    //get all users
    List<User> getAllUsers();
}
