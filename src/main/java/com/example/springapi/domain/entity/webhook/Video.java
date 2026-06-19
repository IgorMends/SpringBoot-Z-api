package com.example.springapi.domain.entity.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    private String videoUrl;
    private Optional<String> caption;
    private Optional<String> mimeType;
    private Optional<String> seconds;
    private Optional<String> width;
    private Optional<String> height;
    private Optional<String> viewOnce;
    private Optional<String> isGif;
}
