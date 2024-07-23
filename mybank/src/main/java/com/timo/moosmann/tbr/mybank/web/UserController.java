package com.timo.moosmann.tbr.mybank.web;

import com.timo.moosmann.tbr.mybank.model.Transaction;
import com.timo.moosmann.tbr.mybank.model.User;
import com.timo.moosmann.tbr.mybank.service.TransactionService;
import com.timo.moosmann.tbr.mybank.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
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

    @GetMapping("/{userId}")
    public String transactions(
            Model model,
            @PathVariable("userId") String userId
    ) {
        if (!userService.doesExist(userId)) {
            return "redirect:/";
        }

        User user = userService.find(userId);
        model.addAttribute("firstName", user);

        List<Transaction> userTransactions = transactionService.findAll(userId);
        model.addAttribute("userTransactions", userTransactions);

        return "userTransactions";
    }
}
