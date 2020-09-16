package com.squad.user.service;

import com.squad.user.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUserId(String userId);
    User save(User user);
    User update(User user);
    boolean delete(String userId);

}
