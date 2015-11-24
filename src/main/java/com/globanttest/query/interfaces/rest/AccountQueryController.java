package com.globanttest.query.interfaces.rest;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.globanttest.query.domain.AccountBalance;
import com.globanttest.query.services.AccountQueryService;

@Controller
public class AccountQueryController {
	
	@Autowired
	private AccountQueryService accountQueryService;

	@RequestMapping(value="/accounts/balance/{accountID}",method = RequestMethod.GET)
	@ResponseBody
	public BigDecimal totalBalanceAccount(@PathVariable Long accountID){
		List<AccountBalance> accountBalances = accountQueryService.accountBalance(accountID);
		BigDecimal balanceTotal = BigDecimal.ZERO;
		for (AccountBalance accountBalance : accountBalances) {
			BigDecimal amount = new BigDecimal(accountBalance.getAmount());
			balanceTotal = balanceTotal.add(amount);
			amount = null;
		}
		return balanceTotal;
	}
	
	@RequestMapping(value="/accounts/{accountID}",method = RequestMethod.GET)
	@ResponseBody
	public List<AccountBalance> allTransactionFromAccount(@PathVariable Long accountID){
		List<AccountBalance> accountBalance = accountQueryService.accountBalance(accountID);
		return accountBalance;
	}
	
	@RequestMapping(value="/accounts",method = RequestMethod.GET)
	@ResponseBody
	public List<AccountBalance> allTransactionAllAccount(){
		List<AccountBalance> accountBalance = accountQueryService.accountAll();
		return accountBalance;
	}
	
	
	
	
}
