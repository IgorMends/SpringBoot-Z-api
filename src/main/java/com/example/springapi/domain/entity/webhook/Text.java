package com.example.springapi.domain.entity.webhook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Text {

    private String message;
    private Optional<String> description;
    private Optional<String> title;
    private Optional<String> url;
    private Optional<String> thumbnailUrl;

}
