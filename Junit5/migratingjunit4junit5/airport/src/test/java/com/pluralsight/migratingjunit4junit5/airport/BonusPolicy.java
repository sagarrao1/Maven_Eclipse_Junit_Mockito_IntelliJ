package com.pluralsight.migratingjunit4junit5.airport;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class BonusPolicy {
    private Flight flight1, flight2, flight3;

    private DistancesManager distancesManager;

    @Given("^we have the flights defined into \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void weHaveTheFlightsDefinedIntoAndAnd(String fileName1, String fileName2, String fileName3) throws Throwable {
        flight1 = FlightBuilderUtil.buildFlightFromCsv("AA1234", 50,"src/test/resources/" + fileName1);
        flight2 = FlightBuilderUtil.buildFlightFromCsv("AA1235", 50,"src/test/resources/" + fileName2);
        flight3 = FlightBuilderUtil.buildFlightFromCsv("AA1236", 50,"src/test/resources/" + fileName3);
    }

    @When("^the passengers travel according to the data recorded into these files$")
    public void thePassengersTravelAccordingToTheDataRecordedIntoTheseFiles() throws Throwable {
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

    @Then("^passenger with identifier \"([^\"]*)\" name \"([^\"]*)\" and countryCode \"([^\"]*)\" has been awarded (\\d+) points$")
    public void passengerWithIdentifierNameAndCountryCodeHasBeenAwardedPoints(String identifier, String name, String countryCode, int points) throws Throwable {
        assertEquals(points, distancesManager.getPassengersPointsMap().get(new Passenger(identifier, name, countryCode)).longValue());
    }
}
