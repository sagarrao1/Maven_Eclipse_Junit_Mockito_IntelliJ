package com.sagar.Junit5Maven;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClinicMain {

   private static ClinicCalendar calendar;

   public static void main(String[] args) throws Throwable {
      calendar = new ClinicCalendar(LocalDate.now());
//      add some resords
      calendar.addAppointment("sagar", "rao", "murphy", "01/04/2021 4:00 pm");
      calendar.addAppointment("saju", "kokkirala", "murphy", "11/22/2021 4:00 pm");
      calendar.addAppointment("Pinky", "shalini", "murphy", "11/23/2021 6:00 pm");
      
      Scanner scanner = new Scanner(System.in);
      System.out.println("Welcome to the Patient Intake Computer System!\n\n");
      String lastOption = "";
      while (!lastOption.equalsIgnoreCase("x")) {
         lastOption = displayMenu(scanner);
      }
      System.out.println("\nExiting System...\n");
   }

   private static String displayMenu(Scanner scanner) throws Throwable {
      System.out.println("Please select an option:");
      System.out.println("1. Enter a Patient Appointment");
      System.out.println("2. View All Appointments");
      System.out.println("3. View Today's Appointments");
      System.out.println("4. Enter Patient Height Weight");
      System.out.println("X.  Exit System.");
      System.out.print("Option: ");
      String option = scanner.next();
      switch (option) {
         case "1": performPatientEntry(scanner);
                 return option;
         case "2": performAllAppointments();
                 return option;
         case "3": performTodayAppointments();
         		 return option;       
         case "4": performHeightWeight(scanner);
 		 		 return option;           		 
         default: System.out.println("Invalid option, please re-enter.");
                  return option;
      }
   }

   
private static void performPatientEntry(Scanner scanner) {
      scanner.nextLine();
      System.out.println("\n\nPlease Enter Appointment Info:");
      System.out.print("  Patient Last Name: ");
      String lastName = scanner.nextLine();
      System.out.print("  Patient First Name: ");
      String firstName = scanner.nextLine();
      System.out.print("  Appointment Date (M/d/yyyy h:m a): ");
      String when = scanner.nextLine();
      System.out.print("  Doctor Last Name: ");
      String doc = scanner.nextLine();
      try {
         calendar.addAppointment(firstName, lastName, doc, when);
      } catch (Throwable t) {
         System.out.println("Error!  " + t.getMessage());
         return;
      }
      System.out.println("Patient entered successfully.\n\n");
   }

   private static void performAllAppointments() throws Throwable {
      System.out.println("\n\nAll Appointments in System:");
      listAppointments(calendar.getAppointments());
      System.out.println("\nPress any key to continue...");
      System.in.read();
      System.out.println("\n\n");
   }

   private static void performTodayAppointments() throws Throwable {
	   System.out.println("\n\nAll Appointments in System:");
	      listAppointments(calendar.getTodayAppointments());
	      System.out.println("\nPress any key to continue...");
	      System.in.read();
	      System.out.println("\n\n");
   }
private static void listAppointments(List<PatientAppointment> appointmnts) {
	for (PatientAppointment appointment : appointmnts) {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
         String apptTime = formatter.format(appointment.getAppointmentDateTime());
         System.out.println(String.format("%s:  %s, %s\t\tDoctor: %s", apptTime, appointment.getPatientLastName(),
            appointment.getPatientFirstName(), appointment.getDoctor().getName()));
      }
}

private static PatientAppointment findPatientAppointment(String lastName, String firstname) {		
	PatientAppointment appoint = calendar.findAppoint(lastName, firstname);
	return appoint;
}


private static void performHeightWeight(Scanner scanner) {
    scanner.nextLine();
    System.out.println("\n\nEnter patient height and weight for today's appointment:");
    System.out.print("  Patient Last Name: ");
    String lastName = scanner.nextLine();
    System.out.print("  Patient First Name: ");
    String firstName = scanner.nextLine();    
    PatientAppointment appt = calendar.findAppoint(lastName, firstName);
    
    if (appt !=null) {    
    	System.out.print("  Patient weight in kg's: ");
        Integer weight = scanner.nextInt();    
        System.out.print("  Patient height in inches: ");
        Integer height = scanner.nextInt();    
        //calculate BMI
        Double roundedToTwoPlaces = BmiCalculator.calculateBmi(weight, height);
        appt.setBmi(roundedToTwoPlaces);
        System.out.println("Set patient BMI to " + roundedToTwoPlaces + "\n\n");        
    } else {
    	System.out.println("Patient not found. \n\n");
    }	
    
}



}