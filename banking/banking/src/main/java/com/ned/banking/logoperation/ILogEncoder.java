package com.ned.banking.logoperation;

import com.ned.banking.model.Account;

public interface ILogEncoder {
	public String encodeInfo(Account account, Object request);
}
