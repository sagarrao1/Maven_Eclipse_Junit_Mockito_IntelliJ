package com.pluralsight.migratingjunit4junit5.airport.database;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.migratingjunit4junit5.airport.Flight;
import com.pluralsight.migratingjunit4junit5.airport.FlightBuilderUtil;


@ExtendWith(MockitoExtension.class)
public class StatisticsTest {
	
	@Spy
	private Database database;

	private List<Flight> flightList;
	
	@BeforeEach
	public void setupDatabase() throws IOException {
		
		flightList = FlightBuilderUtil.buildFlightsListFromCsv("src/test/resources/flights_list.csv");
	}
	
	@Test
	public void testQueriedData() {
		when(database.queryFlightsDatabase()).thenReturn(flightList);		
		
		assertEquals(10, database.queryFlightsDatabase().size());
        assertEquals(2070.0, database.averageDistance(database.queryFlightsDatabase()), 0.01);
        assertEquals(1100, database.minimumDistance(database.queryFlightsDatabase()));
        assertEquals(3100, database.maximumDistance(database.queryFlightsDatabase()));

		
	}
	
	
}
