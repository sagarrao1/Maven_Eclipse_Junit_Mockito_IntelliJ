package com.pluralsight.tddjunit5.airport.phase1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

public class AirportTest3WithJunit5Features {	
	
	@Nested
	@DisplayName("Given there is a economy flight")
	class EconomyFlightTest {
		
		public Flight economyFlight;
        private Passenger mike;
        private Passenger john;

		
		@BeforeEach
		public void setup() {
			economyFlight = new EconomyFlight("1");
			mike  = new Passenger("Mike", false);
            john = new Passenger("John", true);
		}
		
		@Nested
        @DisplayName("When we have a usual passenger")
        class UsualPassenger {			
			
			@Test
			@DisplayName("Then you can add and remove him from an economy flight")
		    public void testEconomyFlightUsualPassenger() {		       
				assertAll( "Verify all conditions for a usual passenger and an economy flight",
						() -> assertEquals("1", economyFlight.getId()),
						() -> assertEquals(true, economyFlight.addPassenger(mike)),		
						() -> assertEquals(1, economyFlight.getPassengersList().size()) ,
						() -> assertEquals("Mike", economyFlight.passengersList.get(0).getName()), 
						() -> assertEquals(true, economyFlight.removePassenger(mike)),
						() -> assertEquals(0, economyFlight.getPassengersList().size()) 
						);
			}		   
		}
		
		@Nested
        @DisplayName("When we have a VIP passenger")
        class VIPPassenger {			
			
				@Test
				@DisplayName("Then you can add him but cannot remove him from an economy flight")
			    public void testEconomyFlightVipPassenger() {
					
					 assertAll("Verify all conditions for a VIP passenger and an economy flight",
							  () -> assertEquals("1", economyFlight.getId()),
							  () -> assertEquals(true, economyFlight.addPassenger(john)),
							  () -> assertEquals(1, economyFlight.getPassengersList().size()),
							  () -> assertEquals("John", economyFlight.passengersList.get(0).getName()),
							  () -> assertEquals(false, economyFlight.removePassenger(john)),
							  () -> assertEquals(1, economyFlight.getPassengersList().size())							 
					);
			    }
		}
		 
	}

	@DisplayName("Given there is a business flight")
	@Nested
	class BusinessFlightTest {		
		private Flight businessFlight;
		private Passenger mike;
        private Passenger john;
		
		@BeforeEach
		public void setup() {
			businessFlight = new BusinessFlight("2");
			mike = new Passenger("Mike", false);
			john = new Passenger("John", true);
		}
		
		@Nested
        @DisplayName("When we have a usual passenger")
        class UsualPassenger {
			
			@Test
			@DisplayName("Then you cannot add or remove him from a business flight")
		    public void testBusinessFlightUsualPassenger() {	        
				assertAll( "Verify all conditions for a usual passenger and a business flight",
						() ->assertEquals("2", businessFlight.getId()),
						() ->assertEquals(false, businessFlight.addPassenger(mike)),
						() ->assertEquals(0, businessFlight.getPassengersList().size()),
						() ->assertEquals(false, businessFlight.removePassenger(mike)),
						() ->assertEquals(0, businessFlight.getPassengersList().size())
					);
			 }
		}
		
		@Nested
		@DisplayName("When we have a VIP passenger")
        class VIPPassenger {
			 	
				@Test
				@DisplayName("Then you can add him but cannot remove him from a business flight")
			    public void testBusinessFlightVipPassenger() {
					assertAll( "Verify all conditions for a usual passenger and a business flight",							
							() ->assertEquals("2", businessFlight.getId()),
							() ->assertEquals(true, businessFlight.addPassenger(john)),
							() ->assertEquals(1, businessFlight.getPassengersList().size()),
							() ->assertEquals("John", businessFlight.passengersList.get(0).getName()),

							() ->assertEquals(false, businessFlight.removePassenger(john)),
							() ->assertEquals(1, businessFlight.getPassengersList().size())
			      );  
		    }
		}
	}

    
}
