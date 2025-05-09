package com.pluralsight.migratingjunit4junit5.airport;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight {

	private String flightNumber;
    private int seats;
	private String origin;
    private String destination;
    private boolean flying;
    private int distance;
    private boolean takenOff;
    private boolean landed;
    private Set<Passenger> passengersSet = new HashSet<Passenger>();

    private String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";
    private Pattern pattern = Pattern.compile(flightNumberRegex);

	public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if(!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
		this.flightNumber = flightNumber;
		this.seats = seats;
		this.flying = false;
        this.takenOff = false;
        this.landed = false;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        if(passengersSet.size() > seats) {
            throw new RuntimeException("Cannot reduce the number of seats under the number of existing passengers!");
        }
        this.seats = seats;
    }

    public int getPassengersNumber() {
        return passengersSet.size();
    }

    public boolean addPassenger(Passenger passenger) {
        sellTicket(passenger);
        return passengersSet.add(passenger);
    }

    public boolean removePassenger(Passenger passenger) {
        if(passenger.isVip()) {
            return false;
        }
        return  passengersSet.remove(passenger);
    }

    public Set<Passenger> getPassengersSet() {
        return Collections.unmodifiableSet(passengersSet);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        if(takenOff){
            throw new RuntimeException("Flight cannot change its origin any longer!");
        }
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        if(landed){
            throw new RuntimeException("Flight cannot change its destination any longer!");
        }
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isFlying() {
        return flying;
    }

    public boolean isTakenOff() {
        return takenOff;
    }

    public boolean isLanded() {
        return landed;
    }

    @Override
    public String toString() {
        return "Flight " + getFlightNumber() + " from " + getOrigin() + " to " + getDestination();
    }

    private void sellTicket(Passenger passenger) {
        if(passengersSet.size() >= seats) {
            throw new RuntimeException("Not enough seats!");
        }
        System.out.println("Ticket for " + this + " sold to " + passenger);
    }

    public void takeOff() {
	    System.out.println(this + " is taking off");
	    flying = true;
	    takenOff = true;
    }

    public void land() {
        System.out.println(this + " is landing");
        flying = false;
        landed = true;
    }

}
