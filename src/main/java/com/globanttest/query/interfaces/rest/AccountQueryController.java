package com.globanttest.query.interfaces.rest;

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

	@RequestMapping(value="/accounts/{accountID}",method = RequestMethod.GET)
	@ResponseBody
	public List<AccountBalance> totalBalanceAccount(@PathVariable Long accountID){
		List<AccountBalance> accountBalance = accountQueryService.accountBalance(accountID);
		return accountBalance;
	}
	
	
	
	
}
