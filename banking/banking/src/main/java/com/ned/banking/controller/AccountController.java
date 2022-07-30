package com.ned.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;
import com.ned.banking.model.AccountDepositRequest;
import com.ned.banking.model.AccountTransferRequest;
import com.ned.banking.service.IAccountService;

@RestController
public class AccountController {

	private IAccountService accountService;

	@Autowired
	public AccountController(@Qualifier("AccountService") IAccountService accountService) {
		this.accountService = accountService;
	}

	// Web Service - 1 ) Create Account
	@PostMapping(path = "/accounts")
	public ResponseEntity<Object> createAccount(@RequestBody AccountCreateRequest request) {
		Object responseObject = this.accountService.createAccountWithAllDetails(request);
		return new ResponseEntity<>(responseObject, null, HttpStatus.OK);
	}

	// Web Service - 2 ) Account Detail
	@GetMapping(path = "/accounts/{id}")
	public ResponseEntity<Account> getAccountDetail(@PathVariable int id) {
		Account account = this.accountService.getAccountDetails(id);
		ResponseEntity<Account> responseEntity = new ResponseEntity<Account>(account, null, HttpStatus.OK);
		responseEntity.ok().lastModified(account.getAccountDateOfUpdate().getTime());
		return responseEntity;
	}

	// Web Service - 3 ) Deposite Account
	@PutMapping("/accounts/{id}/balance")
	public ResponseEntity<Account> depositeMoney(@PathVariable int id, @RequestBody AccountDepositRequest request) {
		Account account = this.accountService.depositeToAccount(id, request);
		ResponseEntity<Account> responseEntity = new ResponseEntity<Account>(account, null, HttpStatus.OK);
		responseEntity.ok().lastModified(account.getAccountDateOfUpdate().getTime());
		return responseEntity;
	}

	// Web Service - 4 ) Transfer Money
	@PutMapping("/accounts/{id}/balance/connection")
	public ResponseEntity<Object> transferMoney(@PathVariable int id, @RequestBody AccountTransferRequest request) {
		Object successObject = this.accountService.transferMoney(id, request);
		Account account = this.accountService.getAccountDetails(id);
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(successObject, null, HttpStatus.OK);
		responseEntity.ok().lastModified(account.getAccountDateOfUpdate().getTime());
		return responseEntity;
	}

	// Web Service - 5 ) Get logs
	@GetMapping("/accounts/{id}/logs")
	@CrossOrigin(origins = "http://localhost:6162")
	public ResponseEntity<Object> accountLog(@PathVariable int id) {
		Object logList = this.accountService.getAccountLogs(id);
		return new ResponseEntity<Object>(logList, null, HttpStatus.OK);
	}

	// Web Service - 6 ) Delete Account
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		Object object = this.accountService.deleteAccount(id);
		return new ResponseEntity<Object>(object, null, HttpStatus.OK);
	}
}
