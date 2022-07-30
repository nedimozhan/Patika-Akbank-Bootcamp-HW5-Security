package com.ned.banking.fileoperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ned.banking.model.Account;

public class FileReader {

	public static Account getAccountById(String id) {

		Account account = new Account();
		String fileName = String.valueOf(id) + ".txt";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new java.io.FileReader(fileName));

			// Get Name
			String accountName = reader.readLine();

			// Get Surname
			String accountSurname = reader.readLine();

			// Get Email
			String accountEmail = reader.readLine();

			// Get Tc
			String accountTc = reader.readLine();

			// Get Type
			String accountType = reader.readLine();

			// Get Account Number
			String accountAccountNumber = reader.readLine();

			// Get Balance
			String accountBalance = reader.readLine();

			// Get Update Date
			String accountUpdateDate = reader.readLine();

			// Close file reader
			reader.close();

			account.setName(accountName);
			account.setSurname(accountSurname);
			account.setEmail(accountEmail);
			account.setTc(accountTc);
			account.setType(accountType);
			account.setAccountNumber(accountAccountNumber);
			account.setAccountBalance(Float.parseFloat(accountBalance));
			account.setAccountDateOfUpdate(Timestamp.valueOf(accountUpdateDate));

			return account;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<String> getLogs(String accountNumber) {

		String fileName = "logs.txt";
		ArrayList<String> logArrayList = new ArrayList<String>();

		BufferedReader reader;
		try {
			reader = new BufferedReader(new java.io.FileReader(fileName));

			String line = reader.readLine();
			while (line != null) {

				String tempAccountNumberString = "";

				for (int i = 0; i < 10; i++) {
					tempAccountNumberString += line.charAt(i);
				}

				if (accountNumber.equals(tempAccountNumberString)) {
					logArrayList.add(line);
				}
				// read next line
				line = reader.readLine();
			}
			// Close file reader
			reader.close();

			return logArrayList;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
