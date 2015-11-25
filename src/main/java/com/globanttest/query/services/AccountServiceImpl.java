package com.globanttest.query.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.globanttest.query.domain.AccountBalance;

@Service
//Adapter converts external request and events into commands
public class AccountServiceImpl implements AccountQueryService {
	
	@Autowired
	private MongoOperations mongoOperation;
	

	@Override
	public List<AccountBalance> accountBalance(Long accountID) {
		Query searchUserQuery = new Query(Criteria.where("accountId").is(accountID));

		List<AccountBalance> accountBalances = mongoOperation.find(searchUserQuery, AccountBalance.class);
		return accountBalances;
	}
	
	@Override
	public BigDecimal totalBalance(Long accountID) {
		Query searchUserQuery = new Query(Criteria.where("accountId").is(accountID));

		List<AccountBalance> accountBalances = mongoOperation.find(searchUserQuery, AccountBalance.class);
		BigDecimal balanceTotal = BigDecimal.ZERO;
		for (AccountBalance accountBalance : accountBalances) {
			BigDecimal amount = new BigDecimal(accountBalance.getAmount());
			balanceTotal = balanceTotal.add(amount);
			amount = null;
		}
		return balanceTotal;
	}
	
	@Override
	public List<AccountBalance> accountAll() {
	
		List<AccountBalance> accountBalances = mongoOperation.findAll(AccountBalance.class);
		return accountBalances;
	}

	
	
}
