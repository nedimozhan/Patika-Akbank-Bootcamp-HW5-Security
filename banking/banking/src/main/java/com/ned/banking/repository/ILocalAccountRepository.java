package com.ned.banking.repository;

import java.io.FileNotFoundException;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ILocalAccountRepository {

	public Object getDataById(int id);

	public Object getDataByAccountNumber(String accountNumber);

	public void addData(Object object);

	public void updateData(Object object) throws FileNotFoundException;

	public void deleteData(int id, boolean isDeleted);
}
