package com.squad.user.service;

import com.squad.user.config.KSQLConfig;
import com.squad.user.model.User;
import com.squad.user.producer.UserProducer;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.Row;
import io.confluent.ksql.api.client.StreamedQueryResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@CommonsLog(topic = "User Service Logger")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserProducer userProducer;

    @Override
    public Optional<User> getUserByUserId(String userId) {
        Client client = KSQLConfig.getKsqlClient();
        String query = "SELECT * FROM USERS WHERE USERID='"+userId+"' EMIT CHANGES LIMIT 1;";
        StreamedQueryResult streamedQueryResult = null;
        try {
            streamedQueryResult = client.streamQuery("SELECT * FROM MY_STREAM EMIT CHANGES;").get();
            Row row = streamedQueryResult.poll();
            if (row != null) System.out.println("Row: " + row.values());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
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
