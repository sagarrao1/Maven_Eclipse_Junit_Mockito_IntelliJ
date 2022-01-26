package com.pluralsight.migratingjunit4junit5.airport.database;

import com.pluralsight.migratingjunit4junit5.airport.Flight;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.pluralsight.migratingjunit4junit5.airport.FlightBuilderUtil.buildFlightsListFromCsv;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsTest {
    @Spy
    private Database database;

    private List<Flight> flightsList = new ArrayList<>();

    @Before
    public void setupDatabase() throws IOException {
        flightsList = buildFlightsListFromCsv("src/test/resources/flights_list.csv");
    }

    @Test
    public void testQueriedData() {
        when(database.queryFlightsDatabase()).thenReturn(flightsList);

        assertEquals(10, database.queryFlightsDatabase().size());
        assertEquals(2070.0, database.averageDistance(database.queryFlightsDatabase()), 0.001);
        assertEquals(1100.0, database.minimumDistance(database.queryFlightsDatabase()), 0.001);
        assertEquals(3100.0, database.maximumDistance(database.queryFlightsDatabase()), 0.001);

    }
}
