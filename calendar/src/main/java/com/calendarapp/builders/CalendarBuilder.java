package com.calendarapp.builders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

public class CalendarBuilder {
	private static final String [] MONTHS = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY" , "JUNE", "JULY", "AUGUST", "SEPETEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
	private static final String [] DAYS = {"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
	private static final int DEFAULT_MAX_DAY_SPACES = 5;
	
	
	private int year;
	private int maxDaySpaces = DEFAULT_MAX_DAY_SPACES;
	
	public enum Configuration {
		PADDING("Padding");
		String key;
		Configuration(String key){
			this.key = key;
		}
		public String getKey() {
			return key;
		}
	}
	
	public CalendarBuilder(int year) {
		this.year = year;
	}
	
	public CalendarBuilder(int year, Map<Configuration, Object> configuration) {
		this.year = year;
		Integer iMaxDaySpaces = null;
		if((iMaxDaySpaces = (Integer)configuration.get(Configuration.PADDING)) != null) {
			maxDaySpaces = iMaxDaySpaces;
		}
	}

	private int getMonthSpaces(String month) {
		int maxLen =  month.length();
		int mid =  Math.round((DAYS.length * maxDaySpaces) / 2);
		return mid + Math.round(maxLen/2);
	}

	public void print() {
		singleColumn();
	}
	
	private void singleColumn() {
		for(int monthIdx =0 ; monthIdx < MONTHS.length; monthIdx++) {
			System.out.format("\n\n%"+getMonthSpaces(MONTHS[monthIdx])+"s\n",MONTHS[monthIdx]);
			LocalDate localDate = LocalDate.of(this.year, monthIdx + 1, 1);
			buildDays(monthIdx, localDate);
		}
	}
	private void buildDays(int monthIdx, LocalDate localDate) {
		int maxDayLen = DAYS.length;
		int maxNoOfDays = localDate.lengthOfMonth();
		String separator  = String.join("", Collections.nCopies((maxDayLen-1) * maxDaySpaces, "-"));
		int separatorSpaceLen = maxDayLen * maxDaySpaces;
		int skipSpaceLength = 0;
		System.out.format("%"+separatorSpaceLen+"s\n", separator);
		for(int dayIdx=0; dayIdx < maxDayLen ; dayIdx++) {
			String firstChar = DAYS[dayIdx].substring(0,1);
			if(dayIdx == localDate.getDayOfWeek().getValue()) {
				skipSpaceLength = (dayIdx == maxDayLen) ? (maxDayLen - dayIdx - 1) : dayIdx;
			}
			System.out.format("%"+maxDaySpaces+"s", firstChar);
		}
		System.out.format("\n%"+separatorSpaceLen+"s", separator);
		for(int dayIdx = 1 ; dayIdx <= maxNoOfDays + skipSpaceLength; dayIdx++) {
			if((dayIdx-1)%maxDayLen == 0) {
				System.out.println();
			}
			System.out.format("%"+maxDaySpaces+"s", dayIdx <skipSpaceLength + 1 ? " ": dayIdx - skipSpaceLength);
		}
	}
}
