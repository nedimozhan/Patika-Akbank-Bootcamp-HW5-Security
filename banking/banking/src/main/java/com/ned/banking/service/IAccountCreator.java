package com.ned.banking.service;

import com.ned.banking.model.AccountCreateRequest;

public interface IAccountCreator {

	public Object createAccount(AccountCreateRequest request);
}
