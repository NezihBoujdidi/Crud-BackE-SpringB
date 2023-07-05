package com.example.crud.account;

import java.util.List;

public interface AccountService {
    Account save(Account u);
    List<Account> getAccounts();
    Account update(Account u);
    Account findAccountByEmail(String email);
    void delete(String email);
}
