package com.example.springapi.core.boundary.input;

import com.example.springapi.domain.entity.webhook.Image;
import com.example.springapi.domain.entity.webhook.Text;
import com.example.springapi.domain.entity.webhook.Video;
import lombok.*;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceivedCallbackInput {

    private String instanceId;
    private String messageId;
    private String phone;

    private Optional<Boolean> isStatusReply;
    private Optional<String> senderLid;
    private Optional<String> connectedPhone;
    private Optional<Boolean> waitingMessage;
    private Optional<Boolean> isEdit;
    private Optional<Boolean> isGroup;
    private Optional<Boolean> isNewsletter;
    private Optional<Boolean> fromMe;
    private Optional<Long> momment;
    private Optional<String> status;
    private Optional<String> chatName;
    private Optional<String> senderPhoto;
    private Optional<String> senderName;
    private Optional<String> participantPhone;
    private Optional<String> participantLid;
    private Optional<String> photo;
    private Optional<Boolean> broadcast;
    private Optional<String> type;
    private Optional<String> referenceMessageId;
    private Optional<String> chatLid;
    private Optional<Boolean> forwarded;
    private Optional<Boolean> fromApi;
    private Optional<Boolean> viewOnce;
    private Optional<String> notification;
    private Optional<Object> notificationParameters;
    private Optional<String> callId;
    private Optional<Object> broadcastRecipients;
    private Optional<Object> externalAdReply;

    private Optional<Text> text;
    private Optional<Image> image;
    private Optional<Video> video;
    private Optional<Object> audio;
    private Optional<Object> document;
    private Optional<Object> sticker;
    private Optional<Object> contact;
    private Optional<Object> location;
    private Optional<Object> reaction;
    private Optional<Object> buttonsMessage;
    private Optional<Object> listResponseMessage;
    private Optional<Object> poll;
    private Optional<Object> pollUpdate;
    private Optional<Object> statusImage;
    private Optional<Object> reviewAndPay;

}
