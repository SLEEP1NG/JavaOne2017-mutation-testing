package com.javasig.pitclipse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MeetingTest {

	private static final int NUM_ATTENDEES = 2;

	private Meeting meeting;

	@Before
	public void setUp() {
		meeting = new Meeting(NUM_ATTENDEES);
	}

	@Test
	public void oneAttendee() {
		meeting.register("Jeanne", "CodeRanch");
		meeting.isRegistered("Jeanne");
		meeting.isRoomForMoreAttendees();
		assertEquals(1, meeting.getNumberAttendees());
		assertEquals(0, meeting.getNumberOnWaitlist());
	}

	@Test
	public void maxAttendees() {
		meeting.register("Jeanne", "CodeRanch");
		meeting.register("Maurice", "Morningside Light");
	}
	
	@Test
	public void tooManyAttendees() {
		meeting.register("Jeanne", "CodeRanch");
		meeting.register("Maurice", "Morningside Light");
		meeting.register("Barry", "Drew University");
		assertEquals(1, meeting.getNumberOnWaitlist());
	}

	 @Test
	public void tooManyAttendeesEvenMore() {
		meeting.register("Jeanne", "CodeRanch");
		meeting.register("Maurice", "Morningside Light");
		meeting.register("Barry", "Drew University");
		meeting.register("Victor", "Namura");
	}

	@Test
	public void cancellationMovesUpToWaitList() {
		meeting.register("Jeanne", "CodeRanch");
		meeting.register("Maurice", "Morningside Light");
		meeting.register("Barry", "Drew University");
		meeting.cancelRegistration("Jeanne");
	}

}
