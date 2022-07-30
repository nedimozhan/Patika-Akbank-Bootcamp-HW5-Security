package com.ned.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.ned.banking.accountcreatingop.AccountTimeStampUpdater;
import com.ned.banking.exception.InvalidIdException;
import com.ned.banking.model.Account;
import com.ned.banking.model.AccountDepositRequest;
import com.ned.banking.repository.ILocalAccountRepository;
import com.ned.banking.repository.ILocalUserRepository;

@Component
@Qualifier("AccountMoneyDepositorService")
public class AccountMoneyDepositorService implements IAccountMoneyDepositor {

	private ILocalAccountRepository accountRepository;
	private ILocalUserRepository userRepository;
	
	@Autowired
	public AccountMoneyDepositorService(ILocalAccountRepository accountRepository, ILocalUserRepository userRepository) {
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Account depositeMoney(Account account, AccountDepositRequest request) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		com.ned.banking.model.User user = this.userRepository.loadUserByUsername(username);
		
		if (account.getUserId() != user.getId()) {
			throw new InvalidIdException("Invalid id");
		}
		
		float totalAmount = account.getAccountBalance() + Float.parseFloat(request.getAmount());
		account.setAccountBalance(totalAmount);
		account.setAccountDateOfUpdate(AccountTimeStampUpdater.getTimeStampUpdater().getTimeStamp());

		try {
			this.accountRepository.updateData(account);
			return account;
		} catch (Exception e) {
			return null;
		}
	}
}
