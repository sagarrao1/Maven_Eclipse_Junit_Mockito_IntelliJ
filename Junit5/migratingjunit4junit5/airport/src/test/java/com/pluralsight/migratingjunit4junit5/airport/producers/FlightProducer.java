package com.pluralsight.migratingjunit4junit5.airport.producers;

import com.pluralsight.migratingjunit4junit5.airport.FlightBuilderUtil;
import com.pluralsight.migratingjunit4junit5.airport.Flight;
import com.pluralsight.migratingjunit4junit5.airport.annotations.FlightNumber;

import javax.enterprise.inject.Produces;
import java.io.IOException;

public class FlightProducer {

    @Produces
    @FlightNumber(number= "AA1234")
    public Flight createFlight() throws IOException {
        return FlightBuilderUtil.buildFlightFromCsv("AA1234", 50,"src/test/resources/flights_information.csv");
    }

    @Produces
    @FlightNumber(number= "AA1235")
    public Flight createFlight2() throws IOException {
        return FlightBuilderUtil.buildFlightFromCsv("AA1235", 36,"src/test/resources/flights_information2.csv");
    }

    @Produces
    @FlightNumber(number= "AA1236")
    public Flight createFlight3() throws IOException {
        return  FlightBuilderUtil.buildFlightFromCsv("AA1236", 24,"src/test/resources/flights_information3.csv");
    }
}
