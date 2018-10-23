package com.bottega.withdraw.domain.consumes;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DoRefundMoney {

	private UUID cardId;
	private BigDecimal amount;

}
