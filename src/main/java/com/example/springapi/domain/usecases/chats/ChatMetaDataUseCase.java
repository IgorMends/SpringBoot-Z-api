package com.example.springapi.domain.usecases.chats;

import com.example.springapi.core.boundary.output.chat.ChatMetaDataUseCaseOutput;
import com.example.springapi.domain.entity.Chat;
import com.example.springapi.domain.port.ChatRepository;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatMetaDataUseCase {

    private final ZapiHttpService zapiHttpService;
    private final ChatRepository chatRepository;

    public ChatMetaDataUseCase(ZapiHttpService zapiHttpService, ChatRepository chatRepository){
        this.zapiHttpService = zapiHttpService;
        this.chatRepository = chatRepository;
    }

    public ChatMetaDataUseCaseOutput execute(String instanceId, String instanceToken, String clientToken, String phone){

        Map<String, Object> response = zapiHttpService.get("chats/" + phone ,instanceId, instanceToken, clientToken);

        Chat chat = Chat.builder()
                .phone()
                .name()
                .lid()
                .isGroup()
                .isGroupAnnouncement()
                .archived()
                .pinned()
                .profileThumbnail()
                .isMuted()
                .isMarkedSpam()
                .tags()
                .build();
    }
}
