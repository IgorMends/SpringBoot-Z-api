package com.example.springapi.domain.usecases.chats;

import com.example.springapi.core.boundary.output.chat.ChatMetaDataUseCaseOutput;
import com.example.springapi.domain.entity.Chat;
import com.example.springapi.domain.port.ChatRepository;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ChatMetaDataUseCase {

    private final ZapiHttpService zapiHttpService;
    private final ChatRepository chatRepository;

    public ChatMetaDataUseCase(ZapiHttpService zapiHttpService, ChatRepository chatRepository){
        this.zapiHttpService = zapiHttpService;
        this.chatRepository = chatRepository;
    }

    public ChatMetaDataUseCaseOutput execute(String instanceId, String instanceToken, String clientToken, String phone){
        try {
            Optional<Chat> chatFromDb = chatRepository.findByPhone(phone);

            if (chatFromDb.isPresent()) {
                Chat chat = chatFromDb.get();

                ChatMetaDataUseCaseOutput response = ChatMetaDataUseCaseOutput.builder()
                        .phone(chat.getPhone())
                        .name(chat.getName())
                        .lid(chat.getLid())
                        .isGroup(chat.getIsGroup())
                        .isGroupAnnouncement(chat.getIsGroupAnnouncement())
                        .archived(chat.getArchived())
                        .pinned(chat.getPinned())
                        .profileThumbnail(chat.getProfileThumbnail())
                        .isMuted(chat.getIsMuted())
                        .isMarkedSpam(chat.getIsMarkedSpam())
                        .tags(chat.getTags())
                        .build();

                return response;
            }


            Map<String, Object> response = zapiHttpService.get("chats/" + phone, instanceId, instanceToken, clientToken);

            ChatMetaDataUseCaseOutput output = ChatMetaDataUseCaseOutput.builder()
                    .phone(response.get("phone") != null ? response.get("phone").toString() : null)
                    .name(response.get("name") != null ? response.get("name").toString() : null)
                    .lid(response.get("lid") != null ? response.get("lid").toString() : null)
                    .isGroup(response.get("isGroup") != null ? (Boolean) response.get("isGroup") : null)
                    .isGroupAnnouncement(response.get("isGroupAnnouncement") != null ? (Boolean) response.get("isGroupAnnouncement") : null)
                    .archived(response.get("archived") != null ? response.get("archived").toString() : null)
                    .pinned(response.get("pinned") != null ? response.get("pinned").toString() : null)
                    .profileThumbnail(response.get("profileThumbnail") != null ? response.get("profileThumbnail").toString() : null)
                    .isMuted(response.get("isMuted") != null ? response.get("isMuted").toString() : null)
                    .isMarkedSpam(response.get("isMarkedSpam") != null ? response.get("isMarkedSpam").toString() : null)
                    .tags(response.get("tags") != null ? (List<String>) response.get("tags") : null)
                    .error(null)
                    .build();


            Chat chat = new Chat(
                    output.getPhone(),
                    output.getName(),
                    output.getLid(),
                    output.getIsGroup(),
                    output.getIsGroupAnnouncement(),
                    output.getArchived(),
                    output.getPinned(),
                    output.getProfileThumbnail(),
                    output.getIsMuted(),
                    output.getIsMarkedSpam(),
                    output.getTags()
            );

            chatRepository.save(chat);

            return output;
        } catch (Exception e) {
            return ChatMetaDataUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }


}
