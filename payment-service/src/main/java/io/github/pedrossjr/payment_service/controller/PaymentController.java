package io.github.pedrossjr.payment_service.controller;

import io.github.pedrossjr.common.dto.PaymentRequest;
import io.github.pedrossjr.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> create(
        @RequestHeader("Idempotency-Key") String key,
        @RequestBody PaymentRequest paymentRequest
    ) {
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest, key));
    }
}
