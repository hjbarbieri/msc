package com.globanttest.query.events;

import java.math.BigDecimal;

public class AccountEvent {

	private BigDecimal amount;
	private Long accountId;
	private AccountEventType accountType;

	public AccountEvent(BigDecimal amount, Long transactionId,
			AccountEventType accountType) {
		this.accountId = transactionId;
		this.amount = amount;
		this.accountType = accountType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Long getTransactionId() {
		return accountId;
	}

	public AccountEventType getAccountType() {
		return accountType;
	}

}
