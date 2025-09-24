package com.integration.stripe_payment.service;

import com.integration.stripe_payment.dto.PaymantRequest;
import com.integration.stripe_payment.dto.StripeResponse;
import com.integration.stripe_payment.model.Payment;
import com.integration.stripe_payment.model.PaymentStatus;
import com.integration.stripe_payment.repository.RepositoryPayment;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
//    instruções do stripe
//    precisa dos campos
//    amount, moeda, quantidade, nome

    @Value("${stripe.secretKey}")
    private String secretkey;

    private RepositoryPayment repo;


    public PaymentService(RepositoryPayment repo) {
        this.repo = repo;
    }

    public StripeResponse createCheckout(PaymantRequest request) {
        Stripe.apiKey = secretkey; //conectar a conta do stripe

        // precisamos agora informar ao Stripe que compra um produ chamado x de quanti de 1 e com valor moeda

        SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(request.getName())
                .build();

        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency(request.getCorrecy() == null ? "BRL" : request.getCorrecy())
                .setUnitAmount(request.getAmount())
                .setProductData(productData)

                .build();

        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                .setQuantity(request.getQuantity())
                .setPriceData(priceData)
                .build();


        // agora criaremos uma sesao para passar os dados

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success")
                .setCancelUrl("http://localhost:8080/cancel")
                .addLineItem(lineItem).build();

        try {
            Session session = Session.create(params);

            Payment payment = new Payment();
            payment.setSessionId(session.getId());
            payment.setPaymentIntentId(session.getPaymentIntent());
            payment.setAmount(request.getAmount());
            payment.setCorrecy(request.getCorrecy());
            payment.setName(request.getName());
            payment.setStatus(PaymentStatus.PENDENTE);

            repo.save(payment);

            return StripeResponse.builder()
                    .status(PaymentStatus.APROVADO)
                    .mensage("Sessão de pagamento criada")
                    .sesionID(session.getId())
                    .sessionUrl(session.getUrl())
                    .build();

        } catch (StripeException e) {
            Payment payment = new Payment();
            payment.setAmount(request.getAmount());
            payment.setCorrecy(request.getCorrecy());
            payment.setName(request.getName());
            payment.setStatus(PaymentStatus.RECUSADO);

            repo.save(payment);

            return StripeResponse.builder()
                    .status(PaymentStatus.RECUSADO)
                    .mensage("Erro ao criar sessão: " + e.getMessage())
                    .build();
        }

    }
}


