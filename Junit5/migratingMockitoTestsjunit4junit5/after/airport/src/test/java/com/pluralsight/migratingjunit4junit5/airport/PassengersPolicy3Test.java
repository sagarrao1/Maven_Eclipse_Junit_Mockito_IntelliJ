package com.pluralsight.migratingjunit4junit5.airport;

import java.io.IOException;

public class PassengersPolicy3Test extends AbstractPassengersPolicyTest {

    @Override
    public void initializeFlight() throws IOException {
        flight = FlightBuilderUtil.buildFlightFromCsv("AA1236", 50, "src/test/resources/flights_information3.csv");
    }
}
