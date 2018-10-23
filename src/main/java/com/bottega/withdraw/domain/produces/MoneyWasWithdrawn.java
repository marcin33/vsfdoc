package com.bottega.withdraw.domain.produces;

import com.bottega.DomainEvent;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class MoneyWasWithdrawn implements DomainEvent {
	private final UUID cardId;
	private final BigDecimal amount;
}
