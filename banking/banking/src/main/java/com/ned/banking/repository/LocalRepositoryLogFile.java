package com.ned.banking.repository;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import com.ned.banking.logoperation.LogSeperator;

//*************************** Data layer has changed !! ********************************\\

// @Component
// @Qualifier("LocalRepositoryLogFile")
public class LocalRepositoryLogFile implements ILocalAccountRepository {

	LogSeperator logSeperator;

	public LogSeperator getLogSeperator() {
		return logSeperator;
	}

	@Autowired
	public void setLogSeperator(LogSeperator logSeperator) {
		this.logSeperator = logSeperator;
	}

	@Override
	public Object getDataById(int id) {

		return logSeperator.getLogList(String.valueOf(id));
	}

	@Override
	public void addData(Object object) {

	}

	@Override
	public void updateData(Object object) throws FileNotFoundException {

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
