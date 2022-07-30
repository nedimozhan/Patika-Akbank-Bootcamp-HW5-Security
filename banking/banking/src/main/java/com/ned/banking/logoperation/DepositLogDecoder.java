package com.ned.banking.logoperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.fileoperations.FileReader;

@Component
@Qualifier("DepositLogDecoder")
public class DepositLogDecoder implements ILogDecoder {

	@Override
	public String decodeLogs(String log) {

		List<String> infoLog = new ArrayList<String>(Arrays.asList(log.split(" ")));

		String accountNumber = infoLog.get(0);
		String accountType = FileReader.getAccountById(accountNumber).getType();
		String accountAction = infoLog.get(1);
		String amountInfo = infoLog.get(2);

		String amount = "";

		boolean control = false;
		for (int i = 0; i < amountInfo.length(); i++) {
			if (control == true) {
				amount += amountInfo.charAt(i);
			}
			if (amountInfo.charAt(i) == ':') {
				control = true;
			}
		}
		// 2341231231 deposit amount:100
		// 2341231231 nolu hesaba 100 [hesap tipi] yatırılmıştır.
		String resultLog = accountNumber + " nolu hesaba " + amount + " " + accountType + " yatirilmistir.";

		return resultLog;
	}
}
