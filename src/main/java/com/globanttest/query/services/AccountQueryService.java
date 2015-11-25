package com.globanttest.query.services;

import java.math.BigDecimal;
import java.util.List;

import com.globanttest.query.domain.AccountBalance;

public interface AccountQueryService {
	
	List<AccountBalance> accountBalance(Long accountID);

	List<AccountBalance> accountAll();

	BigDecimal totalBalance(Long accountID);

}
