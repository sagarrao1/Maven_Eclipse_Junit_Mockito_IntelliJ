package com.pluralsight.migratingjunit4junit5.airport.database;


import com.pluralsight.migratingjunit4junit5.airport.Flight;

import java.util.*;

public class Database {

    private Map<String, String> registeredUsers = new HashMap<>();
    private List<Flight> flightsList = new ArrayList<>();

    public boolean login(Credentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        return registeredUsers.keySet().contains(username) &&
               registeredUsers.get(username).equals(password);
    }

    public void registerUser(String username, String password) {
        registeredUsers.put(username, password);
    }

    public List<Flight> queryFlightsDatabase() {
        return flightsList;
    }

    public double averageDistance(List<Flight> flightsList) {
        return flightsList.stream()
                .mapToDouble(Flight::getDistance)
                .average()
                .getAsDouble();
    }

    public int minimumDistance(List<Flight> flightsList) {
        return flightsList.stream()
                .mapToInt(Flight::getDistance)
                .min()
                .getAsInt();
    }

    public int maximumDistance(List<Flight> flightsList) {
        return flightsList.stream()
                .mapToInt(Flight::getDistance)
                .max()
                .getAsInt();
    }
}
