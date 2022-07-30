package com.ned.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.ned.banking.accountcreatingop.AccountInfoCreator;
import com.ned.banking.exception.InvalidTypeException;
import com.ned.banking.model.Account;
import com.ned.banking.model.AccountCreateRequest;
import com.ned.banking.repository.ILocalAccountRepository;
import com.ned.banking.repository.ILocalUserRepository;
import com.ned.banking.successresponses.AccountCreateSuccessResponse;

@Component
@Qualifier("AccountCreatorService")
public class AccountCreatorService implements IAccountCreator {

	private ILocalAccountRepository accountRepository;
	private ILocalUserRepository userRepository;

	@Autowired
	public AccountCreatorService(ILocalAccountRepository accountRepository,ILocalUserRepository userRepository) {
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Object createAccount(AccountCreateRequest request) {

		String type = request.getType();

		if (type.equals("TL") || type.equals("Dolar") || type.equals("Altın")) {

			Account account = AccountInfoCreator.getCreator().createAccountInfo(request);
			
			// Authanticeated User !!
		    //User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			//com.ned.banking.model.User user = this.userRepository.loadUserByUsername(authUser.getUsername());
			
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();
			com.ned.banking.model.User user = this.userRepository.loadUserByUsername(username);
			
			account.setUserId(user.getId());
		    
			accountRepository.addData(account);
			AccountCreateSuccessResponse successResponse = new AccountCreateSuccessResponse();
			successResponse.setAccountNumber(account.getAccountNumber());
			return successResponse;
		} else {
			throw new InvalidTypeException("Invalid Account Type: " + type);
		}
	}
}
