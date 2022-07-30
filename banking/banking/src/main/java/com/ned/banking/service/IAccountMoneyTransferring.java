package com.ned.banking.service;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountTransferRequest;

public interface IAccountMoneyTransferring {
	public Object transferMoney(Account account, AccountTransferRequest request);
}
