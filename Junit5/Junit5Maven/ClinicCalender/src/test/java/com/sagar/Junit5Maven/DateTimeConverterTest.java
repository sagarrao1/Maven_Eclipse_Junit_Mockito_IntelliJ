package com.sagar.Junit5Maven;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@DisplayName("DateTimeConverterTest")
class DateTimeConverterTest {

	@Test
	@DisplayName("convert string to date correclty")
	void convertToDateFromStringCorreclty() {
		LocalDateTime Ttime = LocalDateTime.of(2021, 1, 4, 16, 0);
		LocalDateTime result = DateTimeConverter.convertToDateFromString("today 4:00 PM", 
				LocalDate.of(2021, 1, 4));
		assertEquals(result, Ttime,
				"Failed to conver today's string to expeted date time was "+Ttime);
	}
	
	@Test
	@Disabled
	@DisplayName("convert string to date correclty withCaseInSensitive today")
	void convertToDateFromStringCorrecltywithCaseInSensitive() {
		LocalDateTime Ttime = LocalDateTime.of(2021, 1, 4, 16, 0);
		LocalDateTime result = DateTimeConverter.convertToDateFromString("ToDay 4:00 PM", 
				LocalDate.of(2021, 1, 4));
		assertEquals(result, Ttime,
				"Failed to conver today's string to expeted date time was "+Ttime);
	}

	@Test
	@Tag("dateTime")
	@DisplayName("convert given pattern to date correclty")
	void convertPatternToDateTime() {
		LocalDateTime result = DateTimeConverter.convertToDateFromString("01/12/2021 5:00 PM",null);
		assertEquals(result, LocalDateTime.of(2021, 1, 12, 17, 0));
	}
	
	@Test
	@DisplayName("throw exception for incorrect date given")
	void throwExceptionIdIncorrectDateString() {
//		LocalDateTime result = DateTimeConverter.convertToDateFromString("01/12/2021/2 5:00 PM",null);		
		Throwable error = assertThrows(RuntimeException.class, () ->
				DateTimeConverter.convertToDateFromString("01/12/2021/2 5:00 PM",null));
		assertEquals("Unable to create date time from: [01/12/2021/2 5:00 PM], please enter with format [M/d/yyyy h:mm a]Text '01/12/2021/2 5:00 PM' could not be parsed at index 10", 
				error.getMessage());
	}
}
