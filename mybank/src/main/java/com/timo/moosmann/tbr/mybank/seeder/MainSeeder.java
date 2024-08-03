package com.timo.moosmann.tbr.mybank.seeder;

import com.timo.moosmann.tbr.mybank.model.User;
import com.timo.moosmann.tbr.mybank.service.TransactionService;
import com.timo.moosmann.tbr.mybank.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MainSeeder {

    private final UserService userService;
    private final TransactionService transactionService;
    private final JdbcTemplate jdbcTemplate;

    public MainSeeder(
            UserService userService,
            TransactionService transactionService,
            JdbcTemplate jdbcTemplate
    ) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void seed() {
        System.out.println("Seeding with example models...");

        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
        jdbcTemplate.execute("""
                CREATE TABLE users(
                    id SERIAL,
                    username TINYTEXT NOT NULL UNIQUE,
                    first_name TINYTEXT,
                    last_name TINYTEXT,
                    created DATETIME
                );
                """);

        User john = userService.create(
                "john1",
                "John",
                "Smith"
        );
        User miles = userService.create(
                "miles_yo",
                "Miles",
                "Baker"
        );
        User mrClooney = userService.create(
                "charles_clooney",
                "Charles",
                "Clooney"
        );

        transactionService.createTransaction(
                john,
                miles,
                3000,
                "Custom skateboard build"
        );
        transactionService.createTransaction(
                john,
                mrClooney,
                700,
                "Organic Fruits and Vegetables"
        );
        transactionService.createTransaction(
                miles,
                john,
                50,
                "Debt for dinner yesterday"
        );
    }
}
