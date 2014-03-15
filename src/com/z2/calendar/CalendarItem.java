package com.z2.calendar;

import java.util.UUID;

public class CalendarItem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	UUID id;
	String type;
	String organizer;
	String title;
	String description;
	
	
	CalendarAttendee[] attendees;
	CalendarAlarm[] alarms;
	String location;
	
	long startTime;
	long duration;
	
	
	CalendarItemStatus status;
	CalendarItemPriority priority;
	
	long nextOccurrance(long time){
		return 0;
	}

	boolean ocurring(long time){
		return false;
	}
	
}
