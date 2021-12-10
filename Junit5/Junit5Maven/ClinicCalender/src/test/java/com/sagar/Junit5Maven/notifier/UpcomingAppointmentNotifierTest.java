package com.sagar.Junit5Maven.notifier;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sagar.Junit5Maven.ClinicCalendar;
import com.sagar.Junit5Maven.notifier.SmtpMessageSenderMockImpl.Message;

class UpcomingAppointmentNotifierTest {	
	private SmtpMessageSenderMockImpl mockSmtpSender;	

	@BeforeEach
	void setup() {
		 mockSmtpSender=  new SmtpMessageSenderMockImpl();
		
	}

	@Test
	void testTomorrowNotification() {
		ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2021, 1, 5));
		calendar.addAppointment("sagar", "rao", "murphy", "01/06/2021 05:00 PM");
		
		UpcomingAppointmentNotifier notifier= new UpcomingAppointmentNotifier(calendar,mockSmtpSender);
		notifier.run();
		Message message = mockSmtpSender.recievedMessages.get(0);	
		
		assertAll( 
				() -> assertEquals("sagarrao@gmail.com", message.address),
				() -> assertEquals("You have an appointment tomorrow at 5:00:PM with Dr. murphy", message.body),
				() ->assertEquals("Appointment Reminder", message.subject)
		 );
	}

}
