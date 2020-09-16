package com.squad.user.service;

import com.squad.user.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUserId(String userId);
    void save(User user);
    void update(User user);
    boolean delete(String userId);

}
