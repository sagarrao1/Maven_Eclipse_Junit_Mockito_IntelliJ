package com.sagar.Junit5Maven;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ClinicCalendarTest {
	
	private ClinicCalendar calendar;

	@BeforeEach
	void setup() {
		calendar = new ClinicCalendar(LocalDate.of(2021, 01, 04));
	}

	@Test
	void allowEntryOfAppointment() {
//		ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		calendar.addAppointment("sagar", "rao", "murphy", "01/04/2021 4:00 pm");
		List<PatientAppointment> appointments= calendar.getAppointments();
		assertNotNull(appointments);
		assertEquals(1, appointments.size());
		PatientAppointment appointment1 = appointments.get(0);
		
//		 using below asserall it show multiple assert failure in one test 
//		using lambda expressions enable commented to know instead of corect ones
		
		assertAll(
//		() -> assertEquals("sagar1", appointment1.getPatientLastName()),
		() -> assertEquals("sagar", appointment1.getPatientLastName()),
//		() -> assertEquals("rao1", appointment1.getPatientFirstName()),
		() -> assertEquals("rao", appointment1.getPatientFirstName()),//		
		() -> assertSame(Doctor.murphy, appointment1.getDoctor()),
		() -> assertEquals("1/4/2021 04:00 PM", 
				appointment1.getAppointmentDateTime().
				format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a"))) 
		);
	}

	@Test
	void returnTrueifThereisAppointment() {
//		ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		calendar.addAppointment("sagar", "rao", "murphy", "01/04/2021 4:00 pm");
		assertTrue(calendar.hasAppointment(LocalDate.of(2021, 01, 04)));		
	}
	
	@Test
	void returnFalseifThereisAppointment() {
//		ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
//		calendar.addAppointment("sagar", "rao", "murphy", "01/05/2021 4:00 pm");
		assertFalse(calendar.hasAppointment(LocalDate.of(2021, 01, 04)));		
	}
	
	@Test
	@Tag("dateTime")
	void returnCurrentDayAppointments() {
//		ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2021, 01, 04));
		calendar.addAppointment("sagar", "rao", "murphy", "today 4:00 pm");
		calendar.addAppointment("ram", "rao", "murphy", "today 6:00 pm");
		calendar.addAppointment("mahesh", "rao", "murphy", "01/02/2021 4:00 pm");
		assertEquals(2, calendar.getTodayAppointments().size());
//		assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());
	}

	
	@Test
	void testFindAppointment() {
//		ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2021, 01, 04));		
		calendar.addAppointment("sagar", "rao", "murphy", "today 4:00 pm");
		calendar.addAppointment("ram", "rao", "murphy", "today 6:00 pm");
//		calendar.addAppointment("mahesh", "rao", "murphy", "01/02/2021 4:00 pm");
		PatientAppointment result = calendar.findAppoint("sagar", "rao");
		assertEquals("sagar", result.getPatientLastName());
	}

	@Test
	@Tag("dateTime")
	void returnTomorrowAppointments() {
//		ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2021, 01, 04));
		LocalDate tomorrow = LocalDate.of(2021, 01, 05);
		calendar.addAppointment("sagar", "rao", "murphy", "today 4:00 pm");
		calendar.addAppointment("ram", "rao", "murphy", "today 6:00 pm");
		calendar.addAppointment("mahesh", "rao", "murphy", "01/05/2021 4:00 pm");
		List<PatientAppointment> tomorrowAppointments = calendar.getTomorrowAppointments();
		assertEquals(1, calendar.getTomorrowAppointments().size());
		assertEquals(tomorrow , tomorrowAppointments.get(0).getAppointmentDateTime().toLocalDate());
	}

	@AfterEach
	void teardown() {
//		calendar = null;
//		System.out.println("in tear down. make calrendar null");
	}	

@Nested
@DisplayName("return upcoming appointments")
class UpcomingAppointments{
	
	@Test
	void whenThereAreNone() {
		List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
		assertEquals(0, appointments.size());		
	}
	
	@Test
	void WhenThereisOneFutureAppointment() {
		calendar.addAppointment("sagar", "rao", "murphy", "today 4:00 pm");
		calendar.addAppointment("ram", "rao", "murphy", "today 6:00 pm");
		calendar.addAppointment("mahesh", "rao", "murphy", "01/05/2021 4:00 pm");		
		List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
		assertEquals(1, appointments.size());		
		}	
	}	
}
