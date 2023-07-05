package com.example.crud.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBUserService implements UserService{
    @Override
    public User save(User u) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User update(User u) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public void delete(String email) {

    }
}
