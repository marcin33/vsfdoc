package com.bottega.withdraw;

import java.util.UUID;

public interface CardRepo {
	CreditCard load(UUID cardId);

	void save(CreditCard card);
}
