package com.globanttest.query.interfaces.rest;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.globanttest.query.MscApplication;
import com.globanttest.query.domain.AccountBalance;
import com.globanttest.query.services.AccountQueryService;
import com.globanttest.query.services.AccountServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MscApplication.class)
@WebIntegrationTest("server.port:8082")
public class AccountQueryControllerTest {
    private RestTemplate restTemplate = new TestRestTemplate();
    
    @Mock
    private MongoOperations mongoOperation;
  
    @Autowired
    private WebApplicationContext context;
    
    @Value("classpath:allTransactionAccountResponse.json")
    private File allTransactionAccountResponse;
    
    @Value("classpath:allTransactionAllAccountResponse.json")
    private File allTransactionAllAccountResponse;

    @Before
    public void setUp(){
    	MockitoAnnotations.initMocks(this);
    	final AccountQueryService service = this.context.getBean(AccountServiceImpl.class);
    	ReflectionTestUtils.setField(service, "mongoOperation", mongoOperation);
    	
    }
    
    @Test
    public void transferAccounts() throws Exception {
          
        
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Query searchUserQuery = new Query(Criteria.where("accountId").is(1L));
        AccountBalance ac = new AccountBalance("12", 1L, "OPEN");
        AccountBalance ac1 = new AccountBalance("10", 1L, "CREDIT");
        AccountBalance ac2 = new AccountBalance("-5", 1L, "DEBIT");
        List<AccountBalance> acs = new ArrayList<>();
        acs.add(ac);
        acs.add(ac1);
        acs.add(ac2);
        Mockito.when(mongoOperation.find(searchUserQuery, AccountBalance.class)).thenReturn(acs);
      
        ResponseEntity<String> apiResponse = restTemplate
                .exchange("http://localhost:8082/accounts/balance/1",HttpMethod.GET,new HttpEntity<>(null,requestHeaders),String.class);

        Assert.assertEquals(apiResponse.getStatusCode(),HttpStatus.OK);
        
        Assert.assertEquals(apiResponse.getBody(),"17");
      
        
    }
    
    @Test
    public void allTransactionsFromAccount() throws Exception {
          
        
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Query searchUserQuery = new Query(Criteria.where("accountId").is(1L));
        AccountBalance ac = new AccountBalance("12", 1L, "OPEN");
        AccountBalance ac1 = new AccountBalance("10", 1L, "CREDIT");
        AccountBalance ac2 = new AccountBalance("-5", 1L, "DEBIT");
        List<AccountBalance> acs = new ArrayList<>();
        acs.add(ac);
        acs.add(ac1);
        acs.add(ac2);
        Mockito.when(mongoOperation.find(searchUserQuery, AccountBalance.class)).thenReturn(acs);
      
        ResponseEntity<String> apiResponse = restTemplate
                .exchange("http://localhost:8082/accounts/1",HttpMethod.GET,new HttpEntity<>(null,requestHeaders),String.class);

        Assert.assertEquals(apiResponse.getStatusCode(),HttpStatus.OK);
        
       	String expected = FileUtils.readFileToString(allTransactionAccountResponse);
        
       	JSONAssert.assertEquals(expected, apiResponse.getBody(), false);
     
        
    }
    
    @Test
    public void allTransactionAllAccount() throws Exception {
          
        
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        AccountBalance ac = new AccountBalance("12", 1L, "OPEN");
        AccountBalance ac1 = new AccountBalance("10", 1L, "CREDIT");
        AccountBalance ac2 = new AccountBalance("-5", 1L, "DEBIT");
        AccountBalance acc = new AccountBalance("1", 2L, "OPEN");
        AccountBalance acc1 = new AccountBalance("10", 2L, "CREDIT");
        AccountBalance acc2 = new AccountBalance("-12", 2L, "DEBIT");
        AccountBalance acc3 = new AccountBalance("-5", 2L, "DEBIT");
        List<AccountBalance> acs = new ArrayList<>();
        acs.add(ac);
        acs.add(ac1);
        acs.add(ac2);
        acs.add(acc);
        acs.add(acc1);
        acs.add(acc2);
        acs.add(acc3);
        Mockito.when(mongoOperation.findAll(AccountBalance.class)).thenReturn(acs);
      
        ResponseEntity<String> apiResponse = restTemplate
                .exchange("http://localhost:8082/accounts",HttpMethod.GET,new HttpEntity<>(null,requestHeaders),String.class);

        Assert.assertEquals(apiResponse.getStatusCode(),HttpStatus.OK);
        
       	String expected = FileUtils.readFileToString(allTransactionAllAccountResponse);
        
       	JSONAssert.assertEquals(expected, apiResponse.getBody(), false);
     
        
    }
}

	