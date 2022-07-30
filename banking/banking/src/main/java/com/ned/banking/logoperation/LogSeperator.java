package com.ned.banking.logoperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.banking.fileoperations.FileReader;
import com.ned.banking.model.Log;

@Component
@Qualifier("LogSeperator")
public class LogSeperator {

	ILogDecoder depositDecoder;
	ILogDecoder transferDecoder;

	public LogSeperator(@Qualifier("DepositLogDecoder") ILogDecoder depositDecoder,
			@Qualifier("TransferLogDecoder") ILogDecoder transferDecoder) {
		this.depositDecoder = depositDecoder;
		this.transferDecoder = transferDecoder;
	}

	public List<Log> getLogList(String accountNumber) {

		List<String> logList = FileReader.getLogs(accountNumber);
		List<Log> resultList = new ArrayList<Log>();

		for (int i = 0; i < logList.size(); i++) {
			List<String> operationControlList = new ArrayList<String>(Arrays.asList(logList.get(i).split(" ")));

			if (operationControlList.get(1).equals("deposit")) {
				String logString = depositDecoder.decodeLogs(logList.get(i));
				Log log = new Log();
				log.setLog(logString);
				resultList.add(log);
			} else if (operationControlList.get(1).equals("transfer")) {
				String logString = transferDecoder.decodeLogs(logList.get(i));
				Log log = new Log();
				log.setLog(logString);
				resultList.add(log);
			}
		}

		return resultList;
	}

}
