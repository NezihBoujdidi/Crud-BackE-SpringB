package com.example.crud.account;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBAccountService implements AccountService {

    @Override
    public Account save(Account u) {
        return null;
    }

    @Override
    public List<Account> getAccounts() {
        return null;
    }

    @Override
    public Account update(Account u) {
        return null;
    }

    @Override
    public Account findAccountByEmail(String email) {
        return null;
    }

    @Override
    public void delete(String email) {

    }
}
