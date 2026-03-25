package io.github.pedrossjr.payment_service.service;

import io.github.pedrossjr.common.dto.PaymentRequest;
import io.github.pedrossjr.payment_service.entity.Payment;
import io.github.pedrossjr.payment_service.messaging.PaymentProducer;
import io.github.pedrossjr.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final IdempotencyService idempotencyService;
    private final PaymentProducer paymentProducer;

    public String createPayment(PaymentRequest request, String idempotencyKey) {

        var existing = idempotencyService.check(idempotencyKey);

        if(existing.isPresent()) {
            return existing.get();
        }

        Payment payment = new Payment();
        payment.setExternalId(UUID.randomUUID().toString());
        payment.setAccountId(request.getAccountId());
        payment.setAmount(request.getAmount());
        payment.setStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(payment);

        paymentProducer.sendPaymentCreatedEvent(payment);

        String response = "Pagamento recebido: " + payment.getExternalId();

        idempotencyService.save(idempotencyKey, response);

        return response;
    }
}