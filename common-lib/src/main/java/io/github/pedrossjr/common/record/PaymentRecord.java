package io.github.pedrossjr.payment_service.record;

import java.math.BigDecimal;

public record PaymentRecord(Long accountId, BigDecimal amount){

}
