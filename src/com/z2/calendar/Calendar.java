package com.z2.calendar;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Calendar implements CalendarInt{

	//data structure calendar queue.
	List<CalendarItem>[] calendarList = (LinkedList<CalendarItem>[])new LinkedList[365];
	
	@Override
	public void add(CalendarItem item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(UUID calendarId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CalendarItem get(UUID calendarId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalendarItem[] find(AttributeFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public long addChangeListener(CalenderChangeListener listener) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeChangeListener(long id) {
		// TODO Auto-generated method stub
		
	}

}
