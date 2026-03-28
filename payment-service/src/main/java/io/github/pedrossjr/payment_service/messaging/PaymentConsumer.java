package io.github.pedrossjr.payment_service.messaging;

import io.github.pedrossjr.common.event.PaymentProcessedEvent;
import io.github.pedrossjr.payment_service.entity.Payment;
import io.github.pedrossjr.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProcessedConsumer {

    private final PaymentRepository repository;

    @KafkaListener(
        topics = "payment-processed",
        groupId = "payment-group"
    )
    public void consume(PaymentProcessedEvent event) {

        Payment payment = repository.findById(event.getPaymentId())
            .orElseThrow();

        payment.setStatus(event.getStatus());
        repository.save(payment);

        System.out.println("📤PaymentProcessedConsumer -> Evento recebido: " + event);
    }
}
