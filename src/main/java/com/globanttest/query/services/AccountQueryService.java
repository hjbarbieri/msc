package com.globanttest.query.services;

import java.util.List;

import com.globanttest.query.domain.AccountBalance;

public interface AccountQueryService {
	
	List<AccountBalance> accountBalance(Long accountID);

}
