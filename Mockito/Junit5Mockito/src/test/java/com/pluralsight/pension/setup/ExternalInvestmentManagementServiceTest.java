package com.pluralsight.pension.setup;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.pension.investment.ExternalInvestmentManagementService;


@ExtendWith(MockitoExtension.class)
class ExternalInvestmentManagementServiceTest {
	
	@Spy
	ExternalInvestmentManagementService underTest;

	@Test
	void testShoudBeAbleToBuyPensionFundIfAccountHasEnoughBalance() {
		
//		when we use spy we should use doReturn().when(null).equals(then )
		
		when(underTest.executeInvestmentTransaction( "Sagar", new BigDecimal(100), "BUY")).thenReturn(true);		
		
		
	}

}
