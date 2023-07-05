package com.example.crud.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository
        extends JpaRepository<Account,Long> {
    Account findAccountByEmail(String email);

    void deleteAccountByEmail(String email);
}
