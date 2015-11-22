package com.globanttest.query.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.globanttest.query.services.AccountQueryService;

@Controller
public class AccountQueryController {
	
	@Autowired
	private AccountQueryService accountQueryService;

	@RequestMapping(value="/accounts/{accountId}",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> totalBalanceAccount(@PathVariable Long accountID){
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	
	
}
