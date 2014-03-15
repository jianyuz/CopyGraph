package com.z2.calendar;

import java.util.UUID;

public interface CalendarInt {
	
	void add(CalendarItem item);
	void remove(UUID calendarId);
	
	CalendarItem get(UUID calendarId);
	
	CalendarItem[] find(AttributeFilter filter);
	
	
	
	long addChangeListener(CalenderChangeListener listener);
	void removeChangeListener(long id);
}
