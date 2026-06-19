package com.example.springapi.domain.interactor.webhook;

import com.example.springapi.core.boundary.input.ReceivedCallbackInput;
import com.example.springapi.domain.entity.webhook.Image;
import com.example.springapi.domain.entity.webhook.MessageType;
import com.example.springapi.domain.usecases.webhook.ImageUseCase;
import com.example.springapi.domain.usecases.webhook.TextUsecase;
import com.example.springapi.domain.usecases.webhook.VideoUseCase;
import org.springframework.stereotype.Service;

@Service
public class ReceivedCallBackInteractor {

    private final TextUsecase textUsecase;
    private final ImageUseCase imageUseCase;
    private final VideoUseCase videoUseCase;

    public ReceivedCallBackInteractor(TextUsecase textUsecase, ImageUseCase imageUseCase, VideoUseCase videoUseCase) {
        this.textUsecase = textUsecase;
        this.imageUseCase = imageUseCase;
        this.videoUseCase = videoUseCase;
    }

    public void execute(ReceivedCallbackInput input){

        MessageType type = MessageType.from(input);

        switch (type) {
            case TEXT -> textUsecase.execute(input);
            case IMAGE -> imageUseCase.execute(input);
            case VIDEO -> videoUseCase.execute(input);
            //case AUDIO -> handleAudio(input.getAudio().get());
        }
    }
}
