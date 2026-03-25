package io.github.pedrossjr.payment_service.repository;

import io.github.pedrossjr.payment_service.entity.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdempotencyRepository extends JpaRepository<IdempotencyKey, Long> {

    Optional<IdempotencyKey> findByKey(String key);
}
