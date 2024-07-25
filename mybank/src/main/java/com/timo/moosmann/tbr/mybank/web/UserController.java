package com.timo.moosmann.tbr.mybank.web;

import com.timo.moosmann.tbr.mybank.dto.UserTransaction;
import com.timo.moosmann.tbr.mybank.exceptions.UserNotFoundException;
import com.timo.moosmann.tbr.mybank.model.Transaction;
import com.timo.moosmann.tbr.mybank.model.User;
import com.timo.moosmann.tbr.mybank.service.TransactionService;
import com.timo.moosmann.tbr.mybank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account/{userId}/transactions")
public class UserController {

    private final TransactionService transactionService;
    private final UserService userService;

    public UserController(
            TransactionService transactionService,
            UserService userService
    ) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User populateUser(@PathVariable("userId") String userId) throws UserNotFoundException {
        if (!userService.doesExist(userId)) {
            throw new UserNotFoundException(userId);
        }
        return userService.find(userId);
    }

    @ModelAttribute("userTransactions")
    public List<Transaction> populateUserTransactions(
            @PathVariable("userId") String userId
    ) throws UserNotFoundException {
        if (!userService.doesExist(userId)) {
            throw new UserNotFoundException(userId);
        }

        return transactionService.findAll(userId);
    }

    @GetMapping
    public String transactions(
            Model model,
            @ModelAttribute("newTransaction") UserTransaction newTransaction,
            @PathVariable("userId") String userId
    ) {
        return "userTransactions";
    }

    @PostMapping
    public String addTransaction(
            @ModelAttribute("newTransaction") @Valid UserTransaction newTransaction,
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
