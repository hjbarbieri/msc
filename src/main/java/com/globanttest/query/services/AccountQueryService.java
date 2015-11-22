package com.globanttest.query.services;

import java.math.BigDecimal;

public interface AccountQueryService {
	
	public void openAccount(BigDecimal balance);

	public void debitAccount(BigDecimal balance, Long transactionID);

	void creditAccount(BigDecimal balance, Long transactionID);

}
