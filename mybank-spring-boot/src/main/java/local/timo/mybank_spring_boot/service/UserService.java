package local.timo.mybank_spring_boot.service;

import local.timo.mybank_spring_boot.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = ((rs, rowNum) -> new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getObject("created", LocalDateTime.class)
    ));

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User create(
            String username,
            String firstName,
            String lastName
    ) {
        LocalDateTime created = LocalDateTime.now();

        KeyHolder idHolder = new GeneratedKeyHolder();

        String insertSql = """
                INSERT INTO users (username, first_name, last_name, created) VALUES (?, ?, ?, ?)
                """;
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    insertSql,
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, username);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setObject(4, created);

            return ps;
        }, idHolder);

        if (idHolder.getKey() == null) {
            throw new RuntimeException("Couldn't obtain id of inserted user");
        }

        return new User(
                (int)idHolder.getKey(),
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
                userRowMapper,
                username
        );
    }

    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM users",
                userRowMapper
        );
    }

    public User get(int userId) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE id = ?",
                userRowMapper,
                userId
        );
    }
}
