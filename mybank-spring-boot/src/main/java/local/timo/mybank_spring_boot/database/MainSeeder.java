package local.timo.mybank_spring_boot.database;

import jakarta.annotation.PostConstruct;
import local.timo.mybank_spring_boot.model.User;
import local.timo.mybank_spring_boot.service.TransactionService;
import local.timo.mybank_spring_boot.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MainSeeder {
    private final UserService userService;
    private final TransactionService transactionService;

    public MainSeeder(
            UserService userService,
            TransactionService transactionService
    ) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @PostConstruct
    public void seed() {
        System.out.println("Seeding with example models...");

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
