package com.integration.stripe_payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymantRequest {
    private Long amount;
    private String name;
    private Long quantity;
    private String correcy;
}
