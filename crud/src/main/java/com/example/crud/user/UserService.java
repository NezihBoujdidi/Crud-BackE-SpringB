package com.example.crud.user;

import java.util.List;

public interface UserService {
    User save(User u);
    List<User> getUsers();
    User update(User u);
    User findUserByEmail(String email);
    void delete(String email);
}
