package com.integration.stripe_payment.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;
    private String paymentIntentId;

    private Long amount;
    private String name;
    private Long quantity;
    private String correcy;
    private String callbackUrl;
    private PaymentStatus status;

    @Column(unique = true)
    private String idepotencyKey;


}
