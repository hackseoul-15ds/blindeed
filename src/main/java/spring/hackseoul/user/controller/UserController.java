package spring.hackseoul.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.hackseoul.user.domain.User;
import spring.hackseoul.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return null;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return null;
    }
}