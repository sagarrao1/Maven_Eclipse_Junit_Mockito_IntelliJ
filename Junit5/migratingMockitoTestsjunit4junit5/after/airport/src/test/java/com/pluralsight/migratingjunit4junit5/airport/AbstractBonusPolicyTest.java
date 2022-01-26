package com.pluralsight.migratingjunit4junit5.airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public abstract class AbstractBonusPolicyTest {
    private Flight flight1, flight2, flight3;

    DistancesManager distancesManager;

    @Nested
    @DisplayName("Given we have the flights defined in CSV files")
    class FlightsTest {
        @BeforeEach
        void weHaveTheFlightsDefined() throws IOException {
            flight1 = FlightBuilderUtil.buildFlightFromCsv("AA1234", 50,"src/test/resources/flights_information.csv");
            flight2 = FlightBuilderUtil.buildFlightFromCsv("AA1235", 50,"src/test/resources/flights_information2.csv");
            flight3 = FlightBuilderUtil.buildFlightFromCsv("AA1236", 50,"src/test/resources/flights_information3.csv");
            thePassengersTravelAccordingToTheDataRecordedIntoTheFiles();
        }

        @Nested
        @DisplayName("When the passengers travel according to the data recorded into these files")
        class PassengersTest {

            @Test
            @DisplayName("The passengers have been awarded a number of points")
            public void passengerWithIdentifierNameAndCountryCodeHasBeenAwardedPoints() {
                checkBonusPolicy();
            }
        }
    }

    private void thePassengersTravelAccordingToTheDataRecordedIntoTheFiles() {
        distancesManager = new DistancesManager();

        for (Passenger passenger : flight1.getPassengersSet()) {
            distancesManager.addDistance(passenger, flight1.getDistance());
        }

        for (Passenger passenger : flight2.getPassengersSet()) {
            distancesManager.addDistance(passenger, flight2.getDistance());
        }

        for (Passenger passenger : flight3.getPassengersSet()) {
            distancesManager.addDistance(passenger, flight3.getDistance());
        }

        distancesManager.calculateGivenPoints();
    }

    public abstract void checkBonusPolicy();

}
