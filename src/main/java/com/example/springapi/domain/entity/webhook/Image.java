package com.example.springapi.domain.entity.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    private String imageUrl;
    private Optional<String> mimeType;
    private Optional<String> downloadError;
    private Optional<String> thumbnailUrl;
    private Optional<String> caption;
    private Optional<String> width;
    private Optional<String> height;
    private Optional<String> viewOnce;

}
