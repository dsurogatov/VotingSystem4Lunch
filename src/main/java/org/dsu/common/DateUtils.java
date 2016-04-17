package org.dsu.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public final class DateUtils {

	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private DateUtils() {
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDate parse2Date(String date) {
		try {
			return LocalDate.parse(date, FORMAT);
		} catch (DateTimeParseException e) {
			throw new VotingSystemException(ExceptionType.PARSE_DATE_FAILED, e);
		}
	}

	public static LocalTime parse2Time(String time) {
		try {
			return LocalTime.parse(time);
		} catch (DateTimeParseException e) {
			throw new VotingSystemException(ExceptionType.PARSE_DATE_FAILED, e);
		}
	}
}
