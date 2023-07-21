package com.example.crud.account;

import com.example.crud.auth.AuthenticationController;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountService service;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

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

    @PostMapping("/saveAcc")
    public Account save(
            @RequestBody Account Account
    ){
        return service.save(Account);
    }

    @PutMapping("/{id}")
    public Account update(
            @PathVariable Long id , @RequestBody Account Account
    ){
        return service.update(id,Account);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable Long id
    ){
        service.deleteById(id);
    }

    @PostMapping("/imageUpload")
    public ResponseEntity<Long> uploadImage(@RequestParam("imageData") MultipartFile file) throws IOException {
        ImageData uploadedImage = service.uploadImage(file);
        Long id = uploadedImage.getId();
        return ResponseEntity.ok(id);
    }

    @GetMapping("/getImage/{id}")
    public ResponseEntity<byte[]>  getImageById(@PathVariable("id") Long id){
        byte[] image = service.getImageById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @DeleteMapping("/deleteImage/{id}")
    public void deleteImageById(
            @PathVariable Long id
    ){
        service.deleteImageById(id);
    }
}
