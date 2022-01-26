package com.pluralsight.migratingjunit4junit5.airport.producers;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.enterprise.inject.Produces;

import com.pluralsight.migratingjunit4junit5.airport.Flight;
import com.pluralsight.migratingjunit4junit5.airport.FlightBuilderUtil;
import com.pluralsight.migratingjunit4junit5.airport.annotations.FlightNumber;

public class FlightProducer {

	@Produces
	@FlightNumber(number = "AA1234")
	public Flight createFlight() throws FileNotFoundException, IOException {
		return FlightBuilderUtil.buildFlightFromCsv("AA1234", 50,"src/test/resources/flights_information.csv");
	}
	
	@Produces
	@FlightNumber(number = "AA1235")
	public Flight createFlight2() throws FileNotFoundException, IOException {
		return FlightBuilderUtil.buildFlightFromCsv("AA1235", 50,"src/test/resources/flights_information.csv");
	}

	@Produces
	@FlightNumber(number = "AA1236")
	public Flight createFlight3() throws FileNotFoundException, IOException {
		return FlightBuilderUtil.buildFlightFromCsv("AA1236", 50,"src/test/resources/flights_information.csv");
	}

	
}
