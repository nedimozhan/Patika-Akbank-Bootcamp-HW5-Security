package com.ned.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.ned.banking.exception.InvalidIdException;
import com.ned.banking.exception.InvalidTypeException;
import com.ned.banking.model.Account;
import com.ned.banking.repository.ILocalAccountRepository;
import com.ned.banking.repository.ILocalUserRepository;

@Component
@Qualifier("AccountDetailService")
public class AccountDetailService implements IAccountDetail {

	private ILocalAccountRepository accountRepository;
	private ILocalUserRepository userRepository;

	@Autowired
	public AccountDetailService(ILocalAccountRepository accountRepository, ILocalUserRepository userRepository) {
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Account getAccountDetail(int id) {
		
		//User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//com.ned.banking.model.User user = this.userRepository.loadUserByUsername(authUser.getUsername());
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		com.ned.banking.model.User user = this.userRepository.loadUserByUsername(username);
		Account account = (Account) this.accountRepository.getDataById(id);
		
		// Control USER.ID == ACCOUNT.USERID ?
		if(user.getId() != account.getUserId()) {
			throw new InvalidIdException("Invalid id");
		}
		
		return account;
	}
}
