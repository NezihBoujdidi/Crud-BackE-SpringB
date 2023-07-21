package com.example.crud.account;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AccountService {
    Account save(Account u);
    ImageData uploadImage(MultipartFile file) throws IOException ;

    public byte[] getImageById(Long id);
    public byte[] getImage(String name);
    List<Account> getAccounts();
    Account update(Long id, Account u);
    Account findAccountByEmail(String email);
    //void delete(String email);
    void deleteById(Long id);

    void deleteImageById(Long id);
}
