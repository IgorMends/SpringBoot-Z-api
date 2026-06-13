package com.example.springapi.domain.entity.webhook;

import com.example.springapi.core.boundary.input.ReceivedCallbackInput;

import java.util.Arrays;
import java.util.function.Predicate;

public enum MessageType {
    TEXT(input -> input.getText().isPresent()),
    IMAGE(input -> input.getImage().isPresent()),
    VIDEO(input -> input.getVideo().isPresent()),
    AUDIO(input -> input.getAudio().isPresent());

    private final Predicate<ReceivedCallbackInput> matcher;

    MessageType(Predicate<ReceivedCallbackInput> matcher) {
        this.matcher = matcher;
    }

    public boolean matches(ReceivedCallbackInput input) {
        return matcher.test(input);
    }

    public static MessageType from(ReceivedCallbackInput input) {
        return Arrays.stream(values())
                .filter(type -> type.matches(input))
                .findFirst()
                .orElse(null);
    }
}
