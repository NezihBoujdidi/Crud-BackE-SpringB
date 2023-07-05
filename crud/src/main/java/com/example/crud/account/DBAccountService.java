package com.example.crud.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBAccountService implements AccountService {

    private final AccountRepository repository;

    @Autowired
    public DBAccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account a) {
        return repository.save(a);
    }

    @Override
    public List<Account> getAccounts() {
        return repository.findAll();
    }

    @Override
    public Account update(Account a) {
        return repository.save(a);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return repository.findAccountByEmail(email);
    }

    @Override
    public void delete(String email) {
        repository.deleteAccountByEmail(email);
    }
}
