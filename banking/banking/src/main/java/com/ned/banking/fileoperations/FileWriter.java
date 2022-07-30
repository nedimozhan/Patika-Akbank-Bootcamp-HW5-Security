package com.ned.banking.fileoperations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.ned.banking.model.Account;

public class FileWriter {

	private static File creatingFile;

	public static boolean resetFile(String fileName) throws FileNotFoundException {

		try {
			fileName = fileName + ".txt";
			creatingFile = new File(fileName);
			PrintWriter writer = new PrintWriter(creatingFile);
			writer.print("");
			writer.close();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static boolean writeToFile(Account account) {

		try {
			String fileName = account.getAccountNumber() + ".txt";
			creatingFile = new File(fileName);
			FileOutputStream fos = new FileOutputStream(creatingFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			bw.write(account.getName());
			bw.newLine();

			bw.write(account.getSurname());
			bw.newLine();

			bw.write(account.getEmail());
			bw.newLine();

			bw.write(account.getTc());
			bw.newLine();

			bw.write(account.getType());
			bw.newLine();

			bw.write(account.getAccountNumber());
			bw.newLine();

			bw.write(String.valueOf(account.getAccountBalance()));
			bw.newLine();

			bw.write(String.valueOf(account.getAccountDateOfUpdate()));
			bw.newLine();

			bw.close();
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	public static boolean writeLogToFile(String message) {
		try {
			File file = new File("logs.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			java.io.FileWriter fileWritter = new java.io.FileWriter(file, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.append(message);
			bufferWritter.append("\n");
			bufferWritter.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
