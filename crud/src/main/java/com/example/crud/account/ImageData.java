package com.example.crud.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "imageData")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageData {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String imageName;
    private String imageType;

    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;
}
