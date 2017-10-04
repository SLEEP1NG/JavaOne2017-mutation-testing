package com.javasig.pitclipse;

import java.util.*;
import java.util.Map.*;

/**
 * Non thread safe class to demonstrate PitClipse use
 * 
 * @author nyjeanne
 *
 */
public class Meeting {

	private final int capacity;
	private Map<String, String> attendees;
	private LinkedHashMap<String, String> waitlist;

	/**
	 * Initialize
	 * 
	 * @param capacity
	 *            number attendees allowed
	 */
	public Meeting(int capacity) {
		attendees = new HashMap<>();
		waitlist = new LinkedHashMap<>();
		this.capacity = capacity;
	}

	/**
	 * Add a new attendee if room or add to wait list if not
	 * 
	 * @param name
	 *            attendee
	 * @param company
	 *            affiliation
	 * @return Whether added to list.
	 */
	public boolean register(String name, String company) {
		if (isRoomForMoreAttendees()) {
			attendees.put(name, company);
			return false;
		}
		waitlist.put(name, company);
		return false;
	}

	/**
	 * Cancel registration and move someone off waitlist if now room
	 * 
	 * @param name
	 */
	public void cancelRegistration(String name) {
		attendees.remove(name);
		if (isRoomForMoreAttendees() && !waitlist.isEmpty()) {
			Entry<String, String> first = waitlist.entrySet().iterator().next();
			attendees.put(first.getKey(), first.getValue());
			waitlist.remove(first.getKey());
		}
	}
	
	/**
	 * Check if attendee is registered
	 * @param name
	 * @return
	 */
	public boolean isRegistered(String name) {
		return attendees.containsKey(name);
	}

	/**
	 * Check if room for more attendees without going on waitlist
	 * @return
	 */
	public boolean isRoomForMoreAttendees() {
		return attendees.size() < capacity;
	}

	public int getNumberAttendees() {
		return attendees.size();
	}

	public int getNumberOnWaitlist() {
		return waitlist.size();
	}

}
