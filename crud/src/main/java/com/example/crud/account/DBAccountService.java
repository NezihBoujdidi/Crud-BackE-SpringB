package com.example.crud.account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DBAccountService implements AccountService {

    private final AccountRepository repository;

    private final ImageDataRepository imageRepo;

    @Autowired
    public DBAccountService(AccountRepository repository, ImageDataRepository imageRepo) {
        this.repository = repository;
        this.imageRepo = imageRepo;
    }

    public ImageData uploadImage(MultipartFile file) throws IOException {

        return imageRepo.save(ImageData.builder()
                .imageName(file.getOriginalFilename())
                .imageType(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
    }

    public byte[] getImageById(Long id){
        Optional<ImageData> dbImage = imageRepo.findById(id);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }

    @Transactional
    public ImageData getInfoByImageByName(String name) {
        Optional<ImageData> dbImage = imageRepo.findByImageName(name);

        return ImageData.builder()
                .imageName(dbImage.get().getImageName())
                .imageType(dbImage.get().getImageType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageData> dbImage = imageRepo.findByImageName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }

    public void deleteImageById(Long id) { imageRepo.deleteById(id);}
    @Override
    public Account save(Account a) {
        return repository.save(a);
    }

    @Override
    public List<Account> getAccounts() {
        return repository.findAll();
    }

    @Override
    public Account update(Long id , Account a) {
        return repository.save(a);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return repository.findAccountByEmail(email);
    }


    @Override
    public void deleteById(Long id){
        repository.deleteAccountById(id);
    }
}
