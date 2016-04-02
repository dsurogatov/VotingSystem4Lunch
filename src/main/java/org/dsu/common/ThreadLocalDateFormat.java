package org.dsu.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class ThreadLocalDateFormat {
	
	private static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private ThreadLocalDateFormat() {
	}

	public static String threadSafeFormat(LocalDate date) {
		return date.format(FORMAT);
	}

	public static LocalDate threadSafeFormat2Date(String date) {
		try {
			return LocalDate.parse(date, FORMAT);
		} catch (DateTimeParseException e) {
			throw new VotingSystemException(ExceptionType.PARSE_DATE_FAILED, e);
		}
	}

}
