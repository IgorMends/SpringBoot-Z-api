package com.example.springapi.application.mapper;

import com.example.springapi.domain.entity.Instance;
import com.example.springapi.infrastructure.persistence.InstanceEntity;

public class InstanceMapper {
    public static Instance toDomain(InstanceEntity instanceEntity){
        return new Instance(
                instanceEntity.getInstanceId(),
                instanceEntity.getName(),
                instanceEntity.getCreated(),
                instanceEntity.getPaymentStatus(),
                instanceEntity.getConnectionStatus(),
                instanceEntity.getAutoReadMessage(),
                instanceEntity.getCallRejectAuto(),
                instanceEntity.getReceivedCallbackUrl()
        );
    }

    public static InstanceEntity toEntity(Instance instance){
        return new InstanceEntity(
                null,
                instance.getId(),
                instance.getName(),
                instance.getCreated(),
                instance.getPaymentStatus(),
                instance.getConnectionStatus(),
                instance.getAutoReadMessage(),
                instance.getCallRejectAuto(),
                instance.getReceivedCallbackUrl()
        );
    }
}
