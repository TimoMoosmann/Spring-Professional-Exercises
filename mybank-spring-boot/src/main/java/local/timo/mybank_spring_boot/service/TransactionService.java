package local.timo.mybank_spring_boot.service;

import local.timo.mybank_spring_boot.model.Transaction;
import local.timo.mybank_spring_boot.model.User;
import org.springframework.beans.factory.annotation.Value;
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
public class TransactionService {
    private final String slogan;

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Transaction> transactionRowMapper;

    public TransactionService(
            @Value("${bank.slogan}") String slogan,
            JdbcTemplate jdbcTemplate,
            UserService userService
    ) {
        this.slogan = slogan;
        this.jdbcTemplate = jdbcTemplate;

        transactionRowMapper = (rs, rowNum) -> new Transaction(
                rs.getInt("id"),
                userService.get(rs.getInt("sending_user_id")),
                userService.get(rs.getInt("receiving_user_id")),
                rs.getInt("amount"),
                rs.getObject("created", LocalDateTime.class),
                rs.getString("reference"),
                slogan
        );
    }

    public Transaction createTransaction(
            User sendingUser,
            User receivingUser,
            Integer amount,
            String reference
    ) {
        LocalDateTime timestamp = LocalDateTime.now();
        String insertStatement = """
                INSERT INTO transactions
                    (sending_user_id, receiving_user_id, amount, created, reference)
                    VALUES (?, ?, ?, ?, ?)
                """;

        KeyHolder idHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    insertStatement,
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, sendingUser.id());
            ps.setInt(2, receivingUser.id());
            ps.setInt(3, amount);
            ps.setObject(4, timestamp);
            ps.setString(5, reference);

            return ps;
        }, idHolder);

        if (idHolder.getKey() == null) {
            throw new RuntimeException("Couldn't obtain key of inserted transaction");
        }

        return new Transaction(
                (int)idHolder.getKey(),
                sendingUser,
                receivingUser,
                amount,
                timestamp,
                reference,
                slogan
        );
    }

    public List<Transaction> findAllOfUser() {
        return jdbcTemplate.query(
                "SELECT * FROM transactions",
                transactionRowMapper
        );
    }

    public List<Transaction> findAllOfUser(String username) {
        String sql = """
                SELECT * FROM transactions
                JOIN users ON users.id = transactions.sending_user_id OR users.id = transactions.receiving_user_id
                WHERE users.username = ?
                """;
        return jdbcTemplate.query(
                sql,
                transactionRowMapper,
                username
        );
    }
}
