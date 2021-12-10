package com.sagar.Junit5Maven.notifier;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.sagar.Junit5Maven.ClinicCalendar;
import com.sagar.Junit5Maven.PatientAppointment;

public class UpcomingAppointmentNotifier {

	private ClinicCalendar calendar;	
	private EmailNotifier notifier;
	
	public UpcomingAppointmentNotifier(ClinicCalendar calendar, EmailNotifier notifier) {
		this.calendar = calendar;
		this.notifier = notifier;
	}

	public void run() {
		calendar.getTomorrowAppointments().stream()
			    .forEach(appt -> {
			    	String email = appt.getPatientLastName()
			    			+appt.getPatientFirstName()+"@gmail.com";
			    	String body=  buildMessageBody(appt);
			    	System.out.println("sending with body : "+body);
			    	notifier.sendNotification("Appointment Reminder", body, email);			    	
			    });
	}
	
	private String buildMessageBody(PatientAppointment appt) {		
		String body = "You have an appointment tomorrow at "+
				appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm:a", Locale.US)) +
				" with Dr. "+ appt.getDoctor();
		
		return body;
	}
}
