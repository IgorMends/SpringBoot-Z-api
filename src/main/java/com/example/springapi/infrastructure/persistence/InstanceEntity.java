package com.example.springapi.infrastructure.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("instance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstanceEntity {

    @Id
    private UUID id;
    @Column("instance_id")
    private String instanceId;
    @Column("name")
    private String name;
    @Column("created")
    private Date created;
    @Column("payment_status")
    private String paymentStatus;
    @Column("connection_status")
    private Boolean connectionStatus;
    @Column("auto_read_message")
    private Boolean autoReadMessage;
    @Column("call_reject_auto")
    private Boolean callRejectAuto;
    @Column("received_callback_url")
    private String receivedCallbackUrl;
}
