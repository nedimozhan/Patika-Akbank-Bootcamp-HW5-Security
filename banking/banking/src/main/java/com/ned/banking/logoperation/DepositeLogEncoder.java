package com.ned.banking.logoperation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountDepositRequest;

@Component
@Qualifier("DepositeLogEncoder")
public class DepositeLogEncoder implements ILogEncoder {

	@Override
	public String encodeInfo(Account account, Object request) {
		AccountDepositRequest depositRequest = (AccountDepositRequest) request;

		// 2341231231 deposit amount:100
		String encodedLog = account.getAccountNumber() + " deposit amount:" + depositRequest.getAmount();
		return encodedLog;
	}
}
