package com.timo.moosmann.tbr.mybank.service;

import com.timo.moosmann.tbr.mybank.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserService {

    private List<User> users = new CopyOnWriteArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User create(
            String username,
            String firstName,
            String lastName
    ) {
        LocalDateTime created = LocalDateTime.now();

       int id = jdbcTemplate.update(
                "INSERT INTO users (username, first_name, last_name, created) VALUES (?, ?, ?, ?)",
                username,
                firstName,
                lastName,
                created
        );

        User user = new User(
                id,
                username,
                firstName,
                lastName,
                LocalDateTime.now()
        );
        users.add(user);

        return user;
    }

    public boolean doesExist(String username) {
        List<String> usernames = users.stream().map(User::username).toList();
        return usernames.contains(username);
    }

    public User find(String username) {
        if (!this.doesExist(username)) {
            return null;
        }

        return users.stream().filter(
                user -> user.username().equals(username)
        ).toList().get(0);
    }
}
