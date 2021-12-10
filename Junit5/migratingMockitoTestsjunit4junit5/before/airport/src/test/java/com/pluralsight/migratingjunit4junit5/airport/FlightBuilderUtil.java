package com.pluralsight.migratingjunit4junit5.airport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightBuilderUtil {

	public static Flight buildFlightFromCsv(String flightNumber, 
			int places, String fileName) throws FileNotFoundException, IOException {
		Flight flight= new Flight(flightNumber, places);
		flight.setOrigin("London");
		flight.setDestination("Hyderabad");
		flight.setDistance(2100);
		
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName)) ) {
			String line=null;
			do {
				line = reader.readLine();
				if (line != null) {
					String[] passengerInfo= line.split(";");
					Passenger passenger = new Passenger(passengerInfo[0].trim(),passengerInfo[1].trim(), passengerInfo[2].trim());
					if (passengerInfo.length == 4) {
						if ("Y".equals(passengerInfo[3].trim())) {
							passenger.setVip(true);
						}
					}
					flight.addPassenger(passenger);
				}
			}while (line != null);
			
		}
		return flight;
	}
	
	
	
    public static List<Flight> buildFlightsListFromCsv(String fileName) throws IOException {
    	List<Flight> flightList = new ArrayList<>();
    	
    	try(BufferedReader reader = new BufferedReader(new FileReader(fileName)) ) {
			String line=null;
			do {
				line = reader.readLine();
				if (line != null) {
					String[] flightInfo= line.split(";");
					Flight flight = new Flight(flightInfo[0].trim(), Integer.valueOf(flightInfo[1].trim()));
					flight.setDistance(Integer.valueOf(flightInfo[2].trim()));
					flightList.add(flight);
				}
			}while (line != null);

		return flightList;
    	}	
    }
      
    
}
