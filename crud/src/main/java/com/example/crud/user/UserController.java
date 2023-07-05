package com.example.crud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public User findByEmail(
            @PathVariable String email
    ){
        return userService.findUserByEmail(email);
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public User save(
            @RequestBody User user
    ){
        return userService.save(user);
    }

    @PutMapping
    public User update(
            @RequestBody User user
    ){
        return userService.update(user);
    }

    @DeleteMapping("/{email}")
    public void delete(
            @PathVariable String email
    ){
        userService.delete(email);
    }
}
