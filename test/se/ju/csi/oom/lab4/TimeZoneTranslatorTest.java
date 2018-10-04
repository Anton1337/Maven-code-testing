package se.ju.csi.oom.lab4;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class TimeZoneTranslatorTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testShiftTimeZone() {
		//DateTime datetimeResult = new DateTime(2018, 8, 27, 9, 0, 0);
		DateTime datetime = new DateTime(2018, 8, 27, 8, 0, 0);
		DateTime newDatetime = TimeZoneTranslator.shiftTimeZone(datetime, 1, 2);
		assertEquals("2018-08-27", newDatetime.toString());
	}

	@Test
	public void testShiftEventTimeZone() {
		DateTime LectureStart = new DateTime(2018, 8, 27, 8, 0, 0);
		DateTime LectureEnd = new DateTime(2018, 8, 27, 9, 45, 0);
		Person johannes = new Person("Johannes Schmidt");
		Person ragnar = new Person("Ragnar Nohre");
		Place HC218 = new Place("Hc218",57.7785672,14.1614833,20.0);
		
		Event firstOomLecture = new Event("OOM 2018 Lecture 1",
				LectureStart,
				LectureEnd,
				new HashSet<>(Arrays.asList(johannes, ragnar)),
				HC218);
		// alaska -9, american samoa -11
		Event firstOomLectureChanged = TimeZoneTranslator.shiftEventTimeZone(firstOomLecture, TimeZone.ALASKA, TimeZone.AMERICAN_SAMOA);
		
		assertEquals(firstOomLectureChanged.getStartDate().toString(), "2018-08-27");
		assertEquals(firstOomLectureChanged.getEndDate().toString(), "2018-08-27");
	}
	
	@Test
	public void testShiftTimeZone2() {
		DateTime datetime = new DateTime(2016, 1, 1, 6, 0, 0);
		
		DateTime newDatetime = TimeZoneTranslator.shiftTimeZone(datetime, TimeZone.CENTRAL_EUROPEAN_TIME.getOffset(), TimeZone.US_PACIFIC.getOffset());
		
		
		assertEquals(newDatetime.toString(), "2015-12-31");
	}
	
	@Test
	public void testDateTime() {
		DateTime datetime = new DateTime(2018, 8, 27, 8, 0, 0);
		DateTime datetime2 = new DateTime("2018-08-27 08:00:00");
		
		assertEquals(datetime2.toString(), "2018-08-27");
		assertEquals(datetime.toString(), "2018-08-27");
	}

}
