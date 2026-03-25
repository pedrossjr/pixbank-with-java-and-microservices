package io.github.pedrossjr.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private Long accountId;
    private BigDecimal amount;
}
