package com.ned.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.ned.banking.accountcreatingop.AccountTimeStampUpdater;
import com.ned.banking.exception.InsufficientBalanceException;
import com.ned.banking.exception.InvalidIdException;
import com.ned.banking.model.Account;
import com.ned.banking.model.AccountTransferRequest;
import com.ned.banking.repository.ILocalAccountRepository;
import com.ned.banking.repository.ILocalUserRepository;
import com.ned.banking.repository.IRemoteCurrencyRepository;
import com.ned.banking.successresponses.AccountTransferSuccessResponse;

@Component
@Qualifier("AccountMoneyTransferService")
public class AccountMoneyTransferService implements IAccountMoneyTransferring {

	private ILocalAccountRepository accountRepository;
	private IRemoteCurrencyRepository currencyRepository;
	private ILocalUserRepository userRepository;

	@Autowired
	public AccountMoneyTransferService(ILocalAccountRepository accountRepository,
			@Qualifier("RemoteCurrencyRepository") IRemoteCurrencyRepository currencyRepository,
			ILocalUserRepository userRepository) {
		this.accountRepository = accountRepository;
		this.currencyRepository = currencyRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Object transferMoney(Account account, AccountTransferRequest request) {
		
		//Control Authantiacted User 
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		com.ned.banking.model.User user = this.userRepository.loadUserByUsername(username);
		
		if (account.getUserId() != user.getId()) {
			throw new InvalidIdException("Invalid id");
		}
		
		String accountNumber = request.getTransferredAccountNumber();
		Account toTransferAccount = (Account) this.accountRepository.getDataByAccountNumber(accountNumber);

		float fromAccountBalance = account.getAccountBalance();
		float toAccountBalance = toTransferAccount.getAccountBalance();
		float amount = Float.parseFloat(request.getAmount());

		if (fromAccountBalance >= amount) {

			if (account.getType().equals("TL") && toTransferAccount.getType().equals("Dolar")) {
				float currency = this.currencyRepository.currencyUSD_TL();
				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) * 1 / currency);
			} else if (account.getType().equals("Dolar") && toTransferAccount.getType().equals("TL")) {
				float currency = this.currencyRepository.currencyUSD_TL();
				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) * currency);
			} else if (account.getType().equals("TL") && toTransferAccount.getType().equals("Altın")) {
				float currency = 1 / this.currencyRepository.currencyGOLD_TL();
				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) * currency);
			} else if (account.getType().equals("Altın") && toTransferAccount.getType().equals("TL")) {
				float currency = this.currencyRepository.currencyGOLD_TL();
				System.out.println("ALTIN : " + String.valueOf(currency));
				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) * currency);
			} else if (account.getType().equals("Altın") && toTransferAccount.getType().equals("Dolar")) {
				float currAltınTL = this.currencyRepository.currencyGOLD_TL();
				float currencyUSDTL = this.currencyRepository.currencyUSD_TL();

				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) * currAltınTL / currencyUSDTL);
			} else if (account.getType().equals("Dolar") && toTransferAccount.getType().equals("Altın")) {
				float currAltınTL = this.currencyRepository.currencyGOLD_TL();
				float currencyUSDTL = this.currencyRepository.currencyUSD_TL();

				fromAccountBalance -= (amount);
				toAccountBalance = toAccountBalance + ((amount) / currAltınTL * currencyUSDTL);
			} else {
				fromAccountBalance -= amount;
				toAccountBalance += amount;
			}

			account.setAccountBalance(fromAccountBalance);
			toTransferAccount.setAccountBalance(toAccountBalance);

			account.setAccountDateOfUpdate(AccountTimeStampUpdater.getTimeStampUpdater().getTimeStamp());
			toTransferAccount.setAccountDateOfUpdate(AccountTimeStampUpdater.getTimeStampUpdater().getTimeStamp());

			try {
				this.accountRepository.updateData(toTransferAccount);
				this.accountRepository.updateData(account);

				AccountTransferSuccessResponse successResponse = new AccountTransferSuccessResponse();
				return successResponse;

			} catch (Exception e) {
				throw new InsufficientBalanceException("UNDEFINED EXCEPTION");
			}
		} else {
			throw new InsufficientBalanceException("Insufficient balance");
		}
	}
}
