package com.example.springapi.application.mapper;

import com.example.springapi.domain.entity.Instance;
import com.example.springapi.infrastructure.persistence.InstanceEntity;

public class InstanceMapper {
    public static Instance toDomain(InstanceEntity instanceEntity){
        return new Instance(
                instanceEntity.getInstanceId(),
                instanceEntity.getName(),
                instanceEntity.getPaymentStatus(),
                instanceEntity.getConnectionStatus()
        );
    }

    public static InstanceEntity toEntity(Instance instance){
        return new InstanceEntity(
                null,
                instance.getId(),
                instance.getName(),
                instance.getPaymentStatus(),
                instance.getConnectionStatus()
        );
    }
}
