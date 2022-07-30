package com.ned.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.model.Account;
import com.ned.banking.repository.ILocalAccountRepository;
import com.ned.banking.successresponses.AccountDeleteSuccessResponse;

@Component
@Qualifier("AccountDeleteService")
public class AccountDeleteService implements IAccountDelete {

	private ILocalAccountRepository accountRepository;

	@Autowired
	public AccountDeleteService(ILocalAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Object deleteAccount(int id) {

		Account account = (Account) this.accountRepository.getDataById(id);

		if (account == null) {
			throw new NullPointerException("There is no account like this");
		}

		account.setDeleted(true);
		this.accountRepository.deleteData(id, true);
		AccountDeleteSuccessResponse accountDeleteSuccessResponse = new AccountDeleteSuccessResponse();
		return accountDeleteSuccessResponse;
	}
}
