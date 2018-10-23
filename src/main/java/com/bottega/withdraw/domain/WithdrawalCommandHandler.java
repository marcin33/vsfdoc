package com.bottega.withdraw.domain;

import com.bottega.withdraw.domain.consumes.DoRefundMoney;
import com.bottega.withdraw.domain.consumes.DoWithdrawMoney;

public class WithdrawalCommandHandler {


	private CardRepo repo;

	public void handle(DoWithdrawMoney command) {

		CreditCard card = repo.load(command.getCardId());
		card.withdraw(command.getAmount());
		repo.save(card);
	}

	public void handle(DoRefundMoney command) {

		CreditCard card = repo.load(command.getCardId());
		card.refund(command.getAmount());
		repo.save(card);
	}


}
