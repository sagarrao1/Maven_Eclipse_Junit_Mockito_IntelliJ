package com.sagar.Junit5Maven;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ClinicCalendar {

	private List<PatientAppointment> appointments;
	private LocalDate today;

	public ClinicCalendar(LocalDate today) {
		this.today = today;
		this.appointments = new ArrayList<>();
	}

	public void addAppointment(String patientLastName,String patientFirstName, String doctorKey, String dateTime) {
		Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
		LocalDateTime localDateTime = DateTimeConverter.convertToDateFromString(dateTime, today);
		PatientAppointment appointment = new PatientAppointment(patientLastName,patientFirstName, localDateTime, doc);
		appointments.add(appointment);
	}

//	created new class DateTimeConverter for this
//	private LocalDateTime convertToDateFromString(String dateTime) {
//		LocalDateTime localDateTime;
//		try {
//			if (dateTime.startsWith("today")) {
//				String[] parts= dateTime.split(" ", 2);
//				 LocalTime time = LocalTime.parse(parts[1].toUpperCase(), DateTimeFormatter.ofPattern("h:mm a", Locale.US));
//				 localDateTime = LocalDateTime.of(today, time);				 
//			}
//			
//		else {
//			localDateTime = LocalDateTime.parse(dateTime.toUpperCase(),
//					DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
//		}
//		} catch (Throwable t) {
//			throw new RuntimeException("Unable to create date time from: [" + dateTime.toUpperCase()
//					+ "], please enter with format [M/d/yyyy h:mm a]" + t.getMessage());
//		}
//		return localDateTime;
//	}

	public List<PatientAppointment> getAppointments() {
		return this.appointments;
	}

	public List<PatientAppointment> getTodayAppointments() {
		return appointments.stream().filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(today))
				.collect(Collectors.toList());
	}

	public List<PatientAppointment> getTomorrowAppointments() {
		LocalDate tomorrow = today.plusDays(1);
		return appointments.stream().filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(tomorrow))
				.collect(Collectors.toList());
	}
	
	public List<PatientAppointment> getUpcomingAppointments() {
		return this.appointments.stream().
				filter(appt -> appt.getAppointmentDateTime()
						.toLocalDate().isAfter(today))
						.collect(Collectors.toList());
	}
	
	public boolean hasAppointment(LocalDate date) {
		return appointments.stream()
				.anyMatch(appt -> appt.getAppointmentDateTime()
						.toLocalDate().equals(date));
		}
	
	public PatientAppointment findAppoint(String lname, String fName) {
//		System.out.println("lname"+lname );
//		System.out.println("size:" + appointments.size());
		
		
		for (PatientAppointment patientAppointment : appointments) {
//			System.out.println("getPatientLastName : "+patientAppointment.getPatientLastName());
				if (patientAppointment.getPatientLastName().equalsIgnoreCase(lname)
						&&patientAppointment.getPatientFirstName().equalsIgnoreCase(fName)) {
					return patientAppointment;
				}
		}
		return null;			
	}



}