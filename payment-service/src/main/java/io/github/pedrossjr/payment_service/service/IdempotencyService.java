package io.github.pedrossjr.payment_service.service;

import io.github.pedrossjr.payment_service.entity.IdempotencyKey;
import io.github.pedrossjr.payment_service.repository.IdempotencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdempotencyService {

    private final IdempotencyRepository idempotencyRepository;

    public Optional<String> check(String key) {
        return idempotencyRepository.findByKey(key)
            .map(IdempotencyKey::getResponse);
    }

    public void save(String key, String response) {
        IdempotencyKey idempotencyKey = new IdempotencyKey();
        idempotencyKey.setKey(key);
        idempotencyKey.setResponse(response);
        idempotencyKey.setCreatedAt(LocalDateTime.now());
        idempotencyRepository.save(idempotencyKey);
    }
}
