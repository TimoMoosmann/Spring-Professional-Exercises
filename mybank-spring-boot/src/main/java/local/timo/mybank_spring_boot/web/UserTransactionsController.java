package local.timo.mybank_spring_boot.web;

import jakarta.validation.Valid;
import local.timo.mybank_spring_boot.dto.UserTransactionForm;
import local.timo.mybank_spring_boot.exceptions.UserNotFoundException;
import local.timo.mybank_spring_boot.model.Transaction;
import local.timo.mybank_spring_boot.model.User;
import local.timo.mybank_spring_boot.service.TransactionService;
import local.timo.mybank_spring_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account/{userId}/transactions")
public class UserTransactionsController {

    private final TransactionService transactionService;
    private final UserService userService;

    public UserTransactionsController(
            TransactionService transactionService,
            UserService userService
    ) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User populateUser(@PathVariable("userId") String userId) throws UserNotFoundException {
        User user = userService.find(userId);

        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        return user;
    }

    @ModelAttribute("userTransactions")
    public List<Transaction> populateUserTransactions(
            @PathVariable("userId") String userId
    ) {
        return transactionService.findAllOfUser(userId);
    }

    @GetMapping
    public String transactions(
            Model model,
            @ModelAttribute("newTransaction") UserTransactionForm newTransaction,
            @PathVariable("userId") String userId
    ) {
        return "userTransactions";
    }

    @PostMapping
    public String addTransaction(
            @ModelAttribute("newTransaction") @Valid UserTransactionForm newTransaction,
            BindingResult bindingResult,
            Model model,
            @PathVariable("userId") String userId
    ) {
        if (bindingResult.hasErrors()) {
            return "userTransactions";
        }

        User sendignUser = userService.find(userId);
        User receivingUser = userService.find(newTransaction.getReceivingUserId());

        transactionService.createTransaction(
                sendignUser,
                receivingUser,
                newTransaction.getAmount(),
                newTransaction.getReference()
        );

        return "redirect:/account/" + userId + "/transactions";
    }

    @ExceptionHandler
    public String handle(UserNotFoundException userNotFoundException) {
        return "redirect:/";
    }
}
