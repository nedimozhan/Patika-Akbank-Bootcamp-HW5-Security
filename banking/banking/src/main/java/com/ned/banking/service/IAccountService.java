package com.ned.banking.service;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;
import com.ned.banking.model.AccountDepositRequest;
import com.ned.banking.model.AccountTransferRequest;

public interface IAccountService {

	public Object createAccountWithAllDetails(AccountCreateRequest request);

	public Account getAccountDetails(int id);

	public Account depositeToAccount(int id, AccountDepositRequest request);

	public Object transferMoney(int id, AccountTransferRequest request);

	public Object getAccountLogs(int id);

	public Object deleteAccount(int id);
}
