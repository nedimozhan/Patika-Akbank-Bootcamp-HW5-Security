package com.ned.banking.logoperation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.model.Account;
import com.ned.banking.model.AccountTransferRequest;

@Component
@Qualifier("TransferLogEncoder")
public class TransferLogEncoder implements ILogEncoder {

	@Override
	public String encodeInfo(Account account, Object request) {

		AccountTransferRequest transferRequest = (AccountTransferRequest) request;

		// 2341231231 transfer amount:100,transferred_account:4513423423
		String encodedLog = account.getAccountNumber() + " transfer amount:" + transferRequest.getAmount()
				+ ",transferred_account:" + transferRequest.getTransferredAccountNumber();

		return encodedLog;
	}
}
