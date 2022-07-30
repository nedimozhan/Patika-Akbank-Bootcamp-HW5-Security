package com.ned.banking.service;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountDepositRequest;

public interface IAccountMoneyDepositor {
	public Account depositeMoney(Account account, AccountDepositRequest request);
}
