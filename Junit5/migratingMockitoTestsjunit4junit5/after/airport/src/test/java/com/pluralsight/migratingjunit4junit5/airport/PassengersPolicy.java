package com.pluralsight.migratingjunit4junit5.airport;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class PassengersPolicy {
    private Flight flight;
    private List<Passenger> usualPassengers = new ArrayList<>();
    private List<Passenger> vipPassengers = new ArrayList<>();
    private Flight anotherFlight = new Flight("AA1234", 48);

    @Given("^there is a flight having number \"([^\"]*)\" and (\\d+) places with passengers defined into \"([^\"]*)\"$")
    public void thereIsAFlightHavingNumberAndPlacesWithPassengersDefinedInto(String flightNumber, int places, String fileName) throws Throwable {
        flight = FlightBuilderUtil.buildFlightFromCsv(flightNumber, places,"src/test/resources/" + fileName);
    }

    @When("^we have usual passengers$")
    public void weHaveUsualPassengers() throws Throwable {
        for (Passenger passenger: flight.getPassengersSet()) {
            if (!passenger.isVip()) {
                usualPassengers.add(passenger);
            }
        }
    }

    @Then("^you can remove them from the flight$")
    public void youCanRemoveThemFromTheFlight() throws Throwable {
        for(Passenger passenger: usualPassengers) {
            assertTrue(flight.removePassenger(passenger));
        }
    }

    @Then("^add them to another flight$")
    public void addThemToAnotherFlight() throws Throwable {
        for(Passenger passenger: usualPassengers) {
            assertTrue(anotherFlight.addPassenger(passenger));
        }
    }

    @When("^we have VIP passengers$")
    public void weHaveVIPPassengers() throws Throwable {
        for (Passenger passenger: flight.getPassengersSet()) {
            if (passenger.isVip()) {
                vipPassengers.add(passenger);
            }
        }
    }

    @Then("^you cannot remove them from the flight$")
    public void youCannotRemoveThemFromTheFlight() throws Throwable {
        for(Passenger passenger: vipPassengers) {
            assertFalse(flight.removePassenger(passenger));
        }
    }
}
