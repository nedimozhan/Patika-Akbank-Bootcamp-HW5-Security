package com.ned.banking.logoperation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.fileoperations.FileReader;

@Component
@Qualifier("TransferLogDecoder")
public class TransferLogDecoder implements ILogDecoder {

	@Override
	public String decodeLogs(String log) {

		// 2341231231 transfer amount:100,transferred_account:4513423423

		List<String> delimiters = Arrays.asList(":", ",");

		for (String delimiter : delimiters) {
			log = log.replace(delimiter, " "); // replace all delimiter occurrence with space
		}

		List<String> infoLog = Arrays.asList(log.split(" ")); // split using space

		String accountNumber = infoLog.get(0); // 2341231231
		String accountType = FileReader.getAccountById(accountNumber).getType(); // TL

		String amountInfo = infoLog.get(3);
		String transferredAcountNumber = infoLog.get(5);

		// 2341231231 hesaptan 4513423423 hesaba 100 [hesap tipi] transfer edilmiştir.
		String resultLog = accountNumber + " hesaptan " + transferredAcountNumber + " hesaba " + amountInfo + " "
				+ accountType + " transfer edilmistir.";

		return resultLog;

	}

}
