package com.ned.banking.service;

import java.util.List;

import com.ned.banking.model.Log;

public interface IAccountLog {
	public List<Log> getAccountLogs(int id);

	public void addLog(Log log);
}
