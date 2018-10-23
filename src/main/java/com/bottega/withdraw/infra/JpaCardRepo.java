package com.bottega.withdraw.infra;

import com.bottega.withdraw.domain.CardRepo;
import com.bottega.withdraw.domain.CreditCard;

import java.util.UUID;

public class JpaCardRepo implements CardRepo {
	@Override
	public CreditCard load(UUID cardId) {
		return null;
	}

	@Override
	public void save(CreditCard card) {

	}
}
