package com.ned.banking.repository;

import org.apache.ibatis.annotations.Mapper;
import com.ned.banking.model.User;

@Mapper
public interface ILocalUserRepository {

	public User loadUserByUsername(String username);
}
