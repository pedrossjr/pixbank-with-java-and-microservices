package io.github.pedrossjr.payment_service.repository;

import io.github.pedrossjr.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
