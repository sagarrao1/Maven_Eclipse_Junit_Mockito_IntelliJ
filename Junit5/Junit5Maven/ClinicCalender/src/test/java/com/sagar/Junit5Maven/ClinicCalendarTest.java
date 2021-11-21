package com.sagar.Junit5Maven;

import static org.junit.jupiter.api.Assertions.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

class ClinicCalendarTest {

	@Test
	void allowEntryOfAppointment() {
		ClinicCalendar calendar = new ClinicCalendar();
		calendar.addAppointment("sagar", "rao", "murphy", "01/04/2021 4:00 pm");
		List<PatientAppointment> appointments= calendar.getAppointments();
		assertNotNull(appointments);
		assertEquals(1, appointments.size());
		PatientAppointment appointment1 = appointments.get(0);
		
		assertEquals("sagar", appointment1.getPatientFirstName());
		assertEquals("rao", appointment1.getPatientLastName());
		assertEquals(Doctor.murphy, appointment1.getDoctor());
		assertEquals("1/4/2021 04:00 PM", 
				appointment1.getAppointmentDateTime().
				format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")) );
		
		
		
		
		
	}

	

}
