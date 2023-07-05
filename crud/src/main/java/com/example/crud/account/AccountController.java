package com.example.crud.account;

import com.example.crud.account.Account;
import com.example.crud.account.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService accountService) {
        this.service = accountService;
    }

    @GetMapping("/{email}")
    public Account findByEmail(
            @PathVariable String email
    ){
        return service.findAccountByEmail(email);
    }

    @GetMapping
    public List<Account> getAccounts(){
        return service.getAccounts();
    }

    @PostMapping
    public Account save(
            @RequestBody Account Account
    ){
        return service.save(Account);
    }

    @PutMapping
    public Account update(
            @RequestBody Account Account
    ){
        return service.update(Account);
    }

    @Transactional
    @DeleteMapping("/{email}")
    public void delete(
            @PathVariable String email
    ){
        service.delete(email);
    }
}
