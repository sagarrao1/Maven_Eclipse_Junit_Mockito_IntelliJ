package com.pluralsight.migratingjunit4junit5.airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractPassengersPolicyTest {
    Flight flight;
    private List<Passenger> usualPassengers = new ArrayList<>();
    private List<Passenger> vipPassengers = new ArrayList<>();
    private Flight anotherFlight = new Flight("AA1233", 48);

    public abstract void initializeFlight() throws IOException;

    @DisplayName("Given there is a flight having a number, a number of places and passengers")
    @Nested
    class FlightTest {

        @BeforeEach
        void thereIsAFlightHavingNumberAndPlacesWithPassengersDefinedInto() throws IOException {
            initializeFlight();
            for (Passenger passenger : flight.getPassengersSet()) {
                if (!passenger.isVip()) {
                    usualPassengers.add(passenger);
                } else {
                    vipPassengers.add(passenger);
                }
            }
        }

        @Nested
        @DisplayName("When we have usual passengers")
        class UsualPassengers {
            @Test
            @DisplayName("Then you can remove them from the flight")
            public void youCanRemoveThemFromTheFlight() {
                for (Passenger passenger : usualPassengers) {
                    assertTrue(flight.removePassenger(passenger));
                }
            }

            @Test
            @DisplayName("And add them to another flight")
            public void addThemToAnotherFlight() {
                for (Passenger passenger : usualPassengers) {
                    assertTrue(anotherFlight.addPassenger(passenger));
                }
            }
        }

        @Nested
        @DisplayName("When we have VIP passengers")
        class VipPassengers {
            @Test
            @DisplayName("Then you cannot remove them from the flight")
            public void youCannotRemoveThemFromTheFlight() {
                for (Passenger passenger : vipPassengers) {
                    assertFalse(flight.removePassenger(passenger));
                }
            }

        }

    }
}
