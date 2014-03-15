package com.z2.calendar;

public interface CalenderChangeListener {

	void itemsAdded(CalendarItem[] items);
	void itemsUpdated(CalendarItem[] items);
	void itemsRemoved(CalendarItem[] items);
}
