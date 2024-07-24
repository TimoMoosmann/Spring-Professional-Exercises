package com.timo.moosmann.tbr.mybank.service;

import com.timo.moosmann.tbr.mybank.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserService {

    private List<User> users = new CopyOnWriteArrayList<>();

    public User create(
            String id,
            String firstName,
            String lastName
    ) {
        // todo: Check if userId already exists

        User user = new User(
                id,
                firstName,
                lastName,
                LocalDateTime.now()
        );
        users.add(user);

        return user;
    }

    public boolean doesExist(String userId) {
        List<String> userIds = users.stream().map(User::id).toList();
        return userIds.contains(userId);
    }

    public User find(String userId) {
        if (!this.doesExist(userId)) {
            throw new RuntimeException("User with id " + userId + " doesn't exist.");
        }

        return users.stream().filter(
                user -> user.id().equals(userId)
        ).toList().get(0);
    }
}
