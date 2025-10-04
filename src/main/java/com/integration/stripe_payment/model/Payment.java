package com.integration.stripe_payment.model;


import com.stripe.model.checkout.Session;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;
    private String sessionUrl;
    private String paymentIntentId;

    private Long amount;
    private String name;
    private Long quantity;
    private String correcy;
    private String callbackUrl;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;



    @Column(unique = true)
    private String idepotency;


}
