package local.timo.mybank_spring_boot.web.api;

import jakarta.validation.Valid;
import local.timo.mybank_spring_boot.dto.UserRequestBody;
import local.timo.mybank_spring_boot.model.User;
import local.timo.mybank_spring_boot.service.UserService;
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
