package com.example.springapi.domain.entity.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Audio {

    private String audioUrl;
    private Optional<Boolean> ptt;
    private Optional<String> seconds;
    private Optional<String> mimeType;
    private Optional<Boolean> viewOnce;
}
