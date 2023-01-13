package dad.custom.components;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static int firstDay(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		return (weekday == 1) ? 7 : weekday - 1;
	}

	public static int lastDay(int year, int month) {
		LocalDate first = LocalDate.of(year, month, 1);
		return first.plusMonths(1).minusDays(1).getDayOfMonth();
	}

	public static Date newDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getTime();
	}

}
