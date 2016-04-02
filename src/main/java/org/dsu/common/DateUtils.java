package org.dsu.common;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {

	private DateUtils() {
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
}
