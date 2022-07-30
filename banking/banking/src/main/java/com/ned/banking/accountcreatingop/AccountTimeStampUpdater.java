package com.ned.banking.accountcreatingop;

import java.sql.Timestamp;

public class AccountTimeStampUpdater {

	private static AccountTimeStampUpdater timeStampUpdater = null;

	private AccountTimeStampUpdater() {

	}

	public static AccountTimeStampUpdater getTimeStampUpdater() {

		if (timeStampUpdater == null) {
			timeStampUpdater = new AccountTimeStampUpdater();
		}
		return timeStampUpdater;
	}

	public Timestamp getTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}
}
