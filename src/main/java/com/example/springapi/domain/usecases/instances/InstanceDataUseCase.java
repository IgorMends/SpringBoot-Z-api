package com.example.springapi.domain.usecases.instances;

import com.example.springapi.core.boundary.output.instance.InstanceDataUseCaseOutput;
import com.example.springapi.domain.entity.Instance;
import com.example.springapi.infrastructure.persistence.InstanceRepositoryImpl;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstanceDataUseCase {

    private final ZapiHttpService zapiHttpService;
    private final InstanceRepositoryImpl instanceRepository;

    public InstanceDataUseCaseOutput execute(String instanceId, String instanceToken, String clientToken){
        try {

            Optional<Instance> instanceFromDb = instanceRepository.findByInstanceId(instanceId);

            if(instanceFromDb.isPresent()){
                Instance instance = instanceFromDb.get();

                InstanceDataUseCaseOutput response = InstanceDataUseCaseOutput.builder()
                        .name(instance.getName())
                        .id(instance.getId())
                        .connected(instance.getConnectionStatus())
                        .paymentStatus(instance.getPaymentStatus())
                        .autoReadMessage(instance.getAutoReadMessage())
                        .callRejectAuto(instance.getCallRejectAuto())
                        .receivedCallbackUrl(instance.getReceivedCallbackUrl())
                        .build();

                return response;
            }

            Map<String, Object> response = zapiHttpService.get("me", instanceId, instanceToken, clientToken);

            InstanceDataUseCaseOutput output = InstanceDataUseCaseOutput.builder()
                    .id(response.get("id") != null ? response.get("id").toString() : null)
                    .token(response.get("token") != null ? response.get("token").toString() : null)
                    .name(response.get("name") != null ? response.get("name").toString() : null)
                    .connected(response.get("connected") != null ? (Boolean)response.get("connected") : null)
                    .created(response.get("created") != null ? response.get("created").toString() : null)
                    .due(response.get("due") != null ? Long.parseLong(response.get("due").toString()) : null)
                    .paymentStatus(response.get("paymentStatus") != null ? response.get("paymentStatus").toString() : null)
                    .callRejectMessage(response.get("callRejectMessage") != null ? response.get("callRejectMessage").toString() : null)
                    .callRejectAuto(response.get("callRejectAuto") != null ? (Boolean)response.get("callRejectAuto") : null)
                    .autoReadMessage(response.get("autoReadMessage") != null ? (Boolean)response.get("autoReadMessage") : null)
                    .receiveCallbackSentByMe(response.get("receiveCallbackSentByMe") != null ? (Boolean)response.get("receiveCallbackSentByMe") : null)
                    .receivedAndDeliveryCallbackUrl(response.get("receivedAndDeliveryCallbackUrl") != null ? response.get("receivedAndDeliveryCallbackUrl").toString() : null)
                    .presenceChatCallbackUrl(response.get("presenceChatCallbackUrl") != null ? response.get("presenceChatCallbackUrl").toString() : null)
                    .disconnectedCallbackUrl(response.get("disconnectedCallbackUrl") != null ? response.get("disconnectedCallbackUrl").toString() : null)
                    .deliveryCallbackUrl(response.get("deliveryCallbackUrl") != null ? response.get("deliveryCallbackUrl").toString() : null)
                    .connectedCallbackUrl(response.get("connectedCallbackUrl") != null ? response.get("connectedCallbackUrl").toString() : null)
                    .messageStatusCallbackUrl(response.get("messageStatusCallbackUrl") != null ? response.get("messageStatusCallbackUrl").toString() : null)
                    .receivedCallbackUrl(response.get("receivedCallbackUrl") != null ? response.get("receivedCallbackUrl").toString() : null)
                    .build();

            OffsetDateTime offset = OffsetDateTime.parse(output.getCreated());
            Date date = Date.from(offset.toInstant());

            Instance instance = new Instance(
                    output.getId(),
                    output.getName(),
                    date,output.getPaymentStatus(),
                    output.getConnected(),
                    output.getAutoReadMessage(),
                    output.getCallRejectAuto(),
                    output.getReceivedCallbackUrl());

            instanceRepository.save(instance, false);

            return output;
        } catch (Exception e) {
            return InstanceDataUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
