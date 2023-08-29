package com.pluralsight.pension.investment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.pension.Account;
import com.pluralsight.pension.investment.ExternalInvestmentManagementService;


@ExtendWith(MockitoExtension.class)
class ExternalInvestmentManagementServiceTest {
	
	public static final String TEST_FUND_ID = "FUND_ID";
	
	@Spy
	ExternalInvestmentManagementService underTest;
	
	@Test
	void testShoudBeAbleToBuyPensionFundIfAccountHasEnoughBalance() throws IOException {
		
//		when we use spy, we should use below syntax .
//		doReturn().when(null).equals(then )
		
		doReturn(true).when(underTest).executeInvestmentTransaction(anyString(), Mockito.any(BigDecimal.class), anyString() );
//		Mockito.when(underTest.executeInvestmentTransaction( anyString(), Mockito.any(BigDecimal.class), anyString() )).thenReturn(true);		
				
		
		Account account = new Account();	
		BigDecimal startingBalance = BigDecimal.valueOf(Long.parseLong("1000"));
		account.setAvailableCash(startingBalance);
		
		
		BigDecimal desiredInvestmentAmt = BigDecimal.valueOf(Long.parseLong("100"));
		account.setInvestments(new HashSet<String>());
		
		underTest.buyInvestmentFund(account, TEST_FUND_ID, desiredInvestmentAmt   );
		
		assertEquals(account.getAvailableCash(),  startingBalance.subtract(desiredInvestmentAmt) );
		assertTrue(account.getInvestments().contains(TEST_FUND_ID));
		
	}
	
	
	@Test
	void testShoudBeAbleToSellPensionFund() throws IOException {
		
		Account account = new Account();
		BigDecimal startingBalance = BigDecimal.valueOf(Long.parseLong("100"));
		account.setAvailableCash(startingBalance);
		
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add(TEST_FUND_ID);
		
		account.setInvestments(hashSet);
		
		BigDecimal desiredInvestmentAmt = BigDecimal.valueOf(Long.parseLong("900"));

		doReturn(true).when(underTest).executeInvestmentTransaction(anyString(), Mockito.any(BigDecimal.class), anyString() );
//		when(underTest.executeInvestmentTransaction( anyString(), Mockito.any(BigDecimal.class), anyString() )).thenReturn(true);	
				
		underTest.sellInvestmentFund(account, TEST_FUND_ID, desiredInvestmentAmt);
		
		assertEquals(account.getAvailableCash(),  startingBalance.add(desiredInvestmentAmt) );
		assertTrue(account.getInvestments().isEmpty());
		
	}
	
	@Test
	void testaddFunds() {
		Account account = new Account();
		BigDecimal startingBalance = BigDecimal.valueOf(Long.parseLong("100"));
		account.setAvailableCash(startingBalance);

		BigDecimal desiredInvestmentAmt = BigDecimal.valueOf(Long.parseLong("900"));		
		underTest.addFunds(account, desiredInvestmentAmt, Currency.getInstance("USD"));
		assertEquals(account.getAvailableCash(),  startingBalance.add(desiredInvestmentAmt) );
		
		
		
	}
	
}
