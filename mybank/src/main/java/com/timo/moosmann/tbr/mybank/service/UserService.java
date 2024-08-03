package com.timo.moosmann.tbr.mybank.service;

import com.timo.moosmann.tbr.mybank.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

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

        return new User(
                id,
                username,
                firstName,
                lastName,
                LocalDateTime.now()
        );
    }

    public boolean doesExist(String username) {
        Integer users = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users WHERE username = ?",
                Integer.class,
                username
        );

        if (users == null) {
            return false;
        }

        return users > 0;
    }

    public User find(String username) {
        if (!this.doesExist(username)) {
            return null;
        }

        return jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE username = ?",
                (rs, rowNum) ->  {
                   return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getObject("created", LocalDateTime.class)
                    );
                },
                username
        );
    }

    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM users",
                (rs, rowNum) -> new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getObject("created", LocalDateTime.class)
                )
        );
    }
}
