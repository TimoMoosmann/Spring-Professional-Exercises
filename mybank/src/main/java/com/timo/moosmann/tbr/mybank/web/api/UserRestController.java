package com.timo.moosmann.tbr.mybank.web.api;

import com.timo.moosmann.tbr.mybank.dto.UserRequestBody;
import com.timo.moosmann.tbr.mybank.model.User;
import com.timo.moosmann.tbr.mybank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody @Valid UserRequestBody userRequestBody) {

        return userService.create(
                userRequestBody.id(),
                userRequestBody.firstName(),
                userRequestBody.lastName()
        );
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }
}
