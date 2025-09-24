package com.integration.stripe_payment.dto;

import com.integration.stripe_payment.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StripeResponse {
  private PaymentStatus status;
    private String mensage;
    private String sesionID;
    private String sessionUrl;

}
