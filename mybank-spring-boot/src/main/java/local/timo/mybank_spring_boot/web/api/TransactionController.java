package local.timo.mybank_spring_boot.web.api;

import jakarta.validation.Valid;
import local.timo.mybank_spring_boot.dto.TransactionRequestBody;
import local.timo.mybank_spring_boot.model.Transaction;
import local.timo.mybank_spring_boot.model.User;
import local.timo.mybank_spring_boot.service.TransactionService;
import local.timo.mybank_spring_boot.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    public TransactionController(
            TransactionService transactionService,
            UserService userService
    ) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping("/transactions")
    public List<Transaction> index() {
        return transactionService.findAllOfUser();
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody @Valid TransactionRequestBody transactionRequestBody) {
        User sendingUser = userService.find(transactionRequestBody.getSendingUserId());
        User receivingUser = userService.find(transactionRequestBody.getReceivingUserId());

        return transactionService.createTransaction(
                sendingUser,
                receivingUser,
                transactionRequestBody.getAmount(),
                transactionRequestBody.getReference()
        );
    }
}
