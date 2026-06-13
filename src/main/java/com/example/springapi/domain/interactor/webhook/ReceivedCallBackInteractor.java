package com.example.springapi.domain.interactor.webhook;

import com.example.springapi.core.boundary.input.ReceivedCallbackInput;
import com.example.springapi.domain.entity.webhook.MessageType;
import com.example.springapi.domain.entity.webhook.Text;
import com.example.springapi.domain.usecases.webhook.TextUsecase;
import org.springframework.stereotype.Service;

@Service
public class ReceivedCallBackInteractor {

    private final TextUsecase textUsecase;

    public ReceivedCallBackInteractor(TextUsecase textUsecase) {
        this.textUsecase = textUsecase;
    }

    public void execute(ReceivedCallbackInput input){

        MessageType type = MessageType.from(input);

        switch (type) {
            case TEXT -> textUsecase.execute(input);
            //case IMAGE -> handleImage(input.getImage().get());
            //case VIDEO -> handleVideo(input.getVideo().get());
            //case AUDIO -> handleAudio(input.getAudio().get());
        }
    }
}
