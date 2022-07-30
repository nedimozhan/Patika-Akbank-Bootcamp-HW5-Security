package com.ned.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ned.banking.logoperation.ILogEncoder;
import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;
import com.ned.banking.model.AccountDepositRequest;
import com.ned.banking.model.AccountTransferRequest;
import com.ned.banking.model.Log;

@Component
@Qualifier("AccountService")
public class AccountService implements IAccountService {

	IAccountCreator accountCreatorService;
	IAccountDetail accountDetailService;
	IAccountMoneyDepositor accountMoneyDepositorService;
	IAccountMoneyTransferring accountMoneyTransferringService;
	IAccountLog accountLogService;
	IAccountDelete accountDeleteService;

	private KafkaTemplate<String, Log> producer;
	private ILogEncoder transferEncoder;
	private ILogEncoder depositEncoder;

	@Autowired
	public void setProducer(KafkaTemplate<String, Log> producer) {
		this.producer = producer;
	}

	@Autowired
	public void setTransferEncoder(@Qualifier("TransferLogEncoder") ILogEncoder transferEncoder) {
		this.transferEncoder = transferEncoder;
	}

	@Autowired
	public void setDepositEncoder(@Qualifier("DepositeLogEncoder") ILogEncoder depositEncoder) {
		this.depositEncoder = depositEncoder;
	}

	@Autowired
	public AccountService(@Qualifier("AccountCreatorService") IAccountCreator accountCreatorService,
			@Qualifier("AccountDetailService") IAccountDetail accountDetailService,
			@Qualifier("AccountMoneyDepositorService") IAccountMoneyDepositor accountMoneyDepositorService,
			@Qualifier("AccountMoneyTransferService") IAccountMoneyTransferring accountMoneyTransferringService,
			@Qualifier("AccountLogService") IAccountLog accountLogService,
			@Qualifier("AccountDeleteService") IAccountDelete accountDeleteService) {

		this.accountCreatorService = accountCreatorService;
		this.accountDetailService = accountDetailService;
		this.accountMoneyDepositorService = accountMoneyDepositorService;
		this.accountMoneyTransferringService = accountMoneyTransferringService;
		this.accountLogService = accountLogService;
		this.accountDeleteService = accountDeleteService;
	}

	@Override
	public Object createAccountWithAllDetails(AccountCreateRequest request) {
		return this.accountCreatorService.createAccount(request);
	}

	@Override
	public Account getAccountDetails(int id) {
		return this.accountDetailService.getAccountDetail(id);
	}

	@Override
	@Transactional
	public Account depositeToAccount(int id, AccountDepositRequest request) {

		Account account = getAccountDetails(id);

		// Encode to (2341231231 deposit amount:100) format
		String encodedLog = this.depositEncoder.encodeInfo(account, request);
		Account updatedAccount = this.accountMoneyDepositorService.depositeMoney(account, request);

		// Create log
		Log log = new Log();
		log.setLog(encodedLog);
		log.setAccountId(updatedAccount.getId());
		producer.send("logs", log);

		return updatedAccount;
	}

	@Override
	@Transactional
	public Object transferMoney(int id, AccountTransferRequest request) {

		// Get account
		Account account = getAccountDetails(id);

		// Encode to (2341231231 transfer amount:100,transferred_account:4513423423)
		// format
		String encodedLog = this.transferEncoder.encodeInfo(account, request);
		Object successObject = this.accountMoneyTransferringService.transferMoney(account, request);

		// Create log
		Log log = new Log();
		log.setLog(encodedLog);
		log.setAccountId(account.getId());
		producer.send("logs", log);

		return successObject;
	}

	@Override
	public Object getAccountLogs(int id) {

		return this.accountLogService.getAccountLogs(id);
	}

	@Override
	public Object deleteAccount(int id) {

		return this.accountDeleteService.deleteAccount(id);
	}
}
