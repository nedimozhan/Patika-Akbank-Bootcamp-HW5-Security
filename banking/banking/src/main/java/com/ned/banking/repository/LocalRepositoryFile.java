package com.ned.banking.repository;

import java.io.FileNotFoundException;

import com.ned.banking.model.Account;

// *************************** Data layer has changed !! ********************************\\

// @Component
// @Qualifier("LocalRepositoryFile")
public class LocalRepositoryFile implements ILocalAccountRepository {

	@Override
	public Account getDataById(int id) {
		return com.ned.banking.fileoperations.FileReader.getAccountById(String.valueOf(id));
	}

	@Override
	public void addData(Object object) {
		Account account = (Account) object;
		com.ned.banking.fileoperations.FileWriter.writeToFile(account);
	}

	@Override
	public void updateData(Object object) throws FileNotFoundException {

		Account account = (Account) object;
		String accountNumber = account.getAccountNumber();
		com.ned.banking.fileoperations.FileWriter.resetFile(accountNumber);
		com.ned.banking.fileoperations.FileWriter.writeToFile(account);
	}

	@Override
	public void deleteData(int id, boolean b) {

	}

	@Override
	public Object getDataByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}
