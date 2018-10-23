package com.bottega.withdraw.domain;

import com.bottega.withdraw.domain.produces.MoneyWasWithdrawn;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static java.math.BigDecimal.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreditCardTest {


	@Test
	public void should_reject_when_limit_exceeded() {

		CreditCard cc = new CreditCard(BigDecimal.TEN, UUID.randomUUID());

		assertThatThrownBy(() -> cc.withdraw(new BigDecimal(11)))
				.isInstanceOf(RuntimeException.class);
	}
	@Test
	public void should_reject_when_limit_exceeded_with_multiple_tx() {

		CreditCard cc = new CreditCard(BigDecimal.TEN, UUID.randomUUID());

		cc.withdraw(ONE);
		cc.withdraw(ONE);
		cc.withdraw(ONE);
		cc.withdraw(ONE);
		cc.withdraw(ONE);
		cc.withdraw(ONE);

		assertThatThrownBy(() -> cc.withdraw(new BigDecimal(6)))
				.isInstanceOf(RuntimeException.class);
	}

	@Test
	public void should_accept_withdrawal_under_limit() {
		UUID cardId = UUID.randomUUID();
		CreditCard cc = new CreditCard(BigDecimal.TEN, cardId);

		cc.withdraw(ONE);

		assertThat(cc.getEvents()).containsOnly(new MoneyWasWithdrawn(cardId,ONE));
	}
}