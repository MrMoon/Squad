package com.squad.user.service;

import com.squad.user.model.User;
import com.squad.user.producer.UserProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@CommonsLog(topic = "User Service Logger")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserProducer userProducer;

    @Override
    public Optional<User> getUserByUserId(String userId) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        user.setUserId(UUID.randomUUID().toString());
        userProducer.produceUser(user.getUserId() , user);
        return user;
    }

    @Override
    public User update(User user) {
        userProducer.produceUser(user.getUserId() , user);
        return user;
    }

    @Override
    public boolean delete(String userId) {
        if(this.getUserByUserId(userId).isPresent()) {
            userProducer.produceUser(userId , null);
            return true;
        }
        return false;
    }
}
