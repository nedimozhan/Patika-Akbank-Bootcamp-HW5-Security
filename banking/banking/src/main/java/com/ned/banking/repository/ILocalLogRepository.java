package com.ned.banking.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ned.banking.model.Log;

@Mapper
public interface ILocalLogRepository {
	public List<Log> getLogByAccountId(int id);

	public void insertLog(Log log);
}
