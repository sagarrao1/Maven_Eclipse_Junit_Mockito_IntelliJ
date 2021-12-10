package com.pluralsight.migratingjunit4junit5.airport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightBuilderUtil {

    public static Flight buildFlightFromCsv(String flightNumber, int seats, String fileName) throws IOException {
        Flight flight = new Flight(flightNumber, seats);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.setDistance(2100);

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] passengerString = line.split(";");
                    Passenger passenger = new Passenger(passengerString[0].trim(), passengerString[1].trim(), passengerString[2].trim());
                    if(passengerString.length == 4) {
                        if ("Y".equals(passengerString[3].trim())) {
                            passenger.setVip(true);
                        }
                    }
                    flight.addPassenger(passenger);
                }
            } while (line != null);

        }

        return flight;
    }

    public static List<Flight> buildFlightsListFromCsv(String fileName) throws IOException {
        List<Flight> flightsList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] flightString = line.split(";");
                    Flight flight = new Flight(flightString[0].trim(), Integer.valueOf(flightString[1].trim()));
                    flight.setDistance(Integer.valueOf(flightString[2].trim()));
                    flightsList.add(flight);
                }
            } while (line != null);

        }

        return flightsList;
    }
}
