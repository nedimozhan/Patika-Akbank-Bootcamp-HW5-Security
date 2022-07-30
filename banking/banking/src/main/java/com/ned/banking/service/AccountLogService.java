package com.ned.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.model.Log;
import com.ned.banking.repository.ILocalLogRepository;

@Component
@Qualifier("AccountLogService")
public class AccountLogService implements IAccountLog {

	private ILocalLogRepository logRepository;

	@Autowired
	public AccountLogService(ILocalLogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@Override
	public List<Log> getAccountLogs(int id) {
		return this.logRepository.getLogByAccountId(id);
	}

	@Override
	public void addLog(Log log) {
		this.logRepository.insertLog(log);
	}
}
