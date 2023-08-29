package com.pluralsight.pension.withdrawal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.pension.Account;
import com.pluralsight.pension.setup.BackgroundCheckResults;
import com.pluralsight.pension.setup.BackgroundCheckService;
import com.pluralsight.pension.setup.BackgroundCheckServiceImpl;

@ExtendWith(MockitoExtension.class)
class testCloseAccount {

//	@Mock
//	BackgroundCheckService backgroundCheckServiceMock
	
//	private BackgroundCheckService backgroundCheckServiceReal = new BackgroundCheckServiceImpl();
	private BackgroundCheckService backgroundCheckServiceMock = mock(BackgroundCheckService.class);
	
    Instant fixedTime = LocalDate.of(2019, 7, 4)
            .atStartOfDay(ZoneId.systemDefault()).toInstant();	
	Clock clock = Clock.fixed(fixedTime, ZoneId.systemDefault());
	
	AccountClosingService accountClosingService;

	@Test
	void shouldDeclineAccountClosingIfHolderisLessthanRetirementAge() throws IOException {
		
		Account account = new Account();
        account.setDob(LocalDate.of(1954, 7, 5));
        AccountClosingService accountClosingService = new AccountClosingService(backgroundCheckServiceMock, clock);
        
		AccountClosingResponse closeAccountResponse = accountClosingService.closeAccount(account);
		assertEquals(AccountClosingStatus.CLOSING_DENIED, closeAccountResponse.status);

		assertEquals(LocalDateTime.ofInstant(fixedTime, ZoneOffset.systemDefault()), closeAccountResponse.getProcessingDate());
		
	}

	@Test
	void testCloseAccount2() throws IOException {
	
		LocalDate dob = LocalDate.of(1936, 1, 1);		
		Account account= new Account();
		account.setDob(dob);
		
		AccountClosingService accountClosingService = new AccountClosingService(backgroundCheckServiceMock, clock);
		
//		doReturn(backgroundCheckResults).when(backgroundCheckService).confirm(anyString(), anyString(), anyString(), eq(dob) );		
		when(backgroundCheckServiceMock.confirm(anyString(), anyString(), anyString(), eq(dob) )).thenReturn(null);
		
		AccountClosingResponse closeAccountResponse = accountClosingService.closeAccount(account);	
		
		assertEquals(AccountClosingStatus.CLOSING_PENDING, closeAccountResponse.status);		
		assertEquals(LocalDateTime.ofInstant(fixedTime, ZoneOffset.systemDefault()), closeAccountResponse.getProcessingDate());	
	}
	
	@Test
	void testCloseAccount3() throws IOException {
//		AccountClosingService accountClosingService = new AccountClosingService(backgroundCheckServiceReal, clock);
		AccountClosingService accountClosingService = new AccountClosingService(backgroundCheckServiceMock, clock);
	
		final LocalDate dob = LocalDate.of(1936, 1, 1);		
		Account account= new Account();
		account.setFistName("Sagar");
		account.setLastName("rao");
		account.setTaxId("1234");
		account.setDob(dob);		
		
		BackgroundCheckResults backgroundCheckResultsReal = new BackgroundCheckResults("LOW", Long.valueOf(100));	
		
		when(backgroundCheckServiceMock.confirm(anyString(), anyString(), anyString(), Mockito.any() )).thenReturn(backgroundCheckResultsReal);
		
		AccountClosingResponse closeAccountResponse = accountClosingService.closeAccount(account);	
		
		assertEquals(AccountClosingStatus.CLOSING_OK, closeAccountResponse.status);		
		assertEquals(LocalDateTime.ofInstant(fixedTime, ZoneOffset.systemDefault()), closeAccountResponse.getProcessingDate());	
	}
	
	
	
	
//	Same testCloseAccount3 in BDD style
	@Test
	void testCloseAccount4() throws IOException {
		
		AccountClosingService accountClosingService = new AccountClosingService(backgroundCheckServiceMock, clock);
		
		final LocalDate dob = LocalDate.of(1936, 1, 1);		
		Account account= new Account();		
		account.setFistName("Sagar");
		account.setLastName("rao");
		account.setTaxId("1234");
		account.setDob(dob);
		
		BackgroundCheckResults backgroundCheckResultsReal = new BackgroundCheckResults("LOW", Long.valueOf(100));	
		
		BDDMockito.given(backgroundCheckServiceMock.confirm("Sagar", "rao", "1234", dob ))
		.willReturn(backgroundCheckResultsReal);
		
		AccountClosingResponse closeAccountResponse = accountClosingService.closeAccount(account);	
		
		assertEquals(AccountClosingStatus.CLOSING_OK, closeAccountResponse.status);		
		assertEquals(LocalDateTime.ofInstant(fixedTime, ZoneOffset.systemDefault()), closeAccountResponse.getProcessingDate());	
	}
}
