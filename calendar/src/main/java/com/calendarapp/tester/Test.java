package com.calendarapp.tester;

import java.util.HashMap;
import java.util.Map;

import com.calendarapp.builders.CalendarBuilder;

/**
 * @author rakeshmohanty
 *
 */
public class Test {
	public static void main(String ...args) {
		Map<CalendarBuilder.Configuration, Object> configuration = new HashMap<>();
		configuration.put(CalendarBuilder.Configuration.PADDING, 5);
		CalendarBuilder builder  = new CalendarBuilder(2022, configuration);
		builder.print();
	}
}
