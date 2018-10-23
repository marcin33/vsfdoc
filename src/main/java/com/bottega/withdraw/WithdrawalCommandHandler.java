package com.bottega.withdraw;

public class WithdrawalCommandHandler {


	private CardRepo repo;

	public void handle(DoWithdrawMoney command) {

		CreditCard card = repo.load(command.getCardId());
		card.withdraw(command.getAmount());
		repo.save(card);
	}
}
