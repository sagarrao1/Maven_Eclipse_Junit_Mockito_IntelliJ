package com.pluralsight.pension.setup;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pluralsight.pension.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountOpeningServiceTest {

	@Mock
	private BackgroundCheckService backgroundCheckService;

	@Mock
	private ReferenceIdsManager referenceIdsManager;

	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private AccountOpeningEventPublisher eventPublisher;

	@InjectMocks
	AccountOpeningService openingService;

	private static final String FIRST_NAME = "sagar";
	private static final String LAST_NAME = "rao";
	private static final String TAX_ID = "T1234";
	private static final LocalDate DOB = LocalDate.of(2023, 8, 17);
	private static final String UNACCEPTABLE_RISK_PROFILE = "HIGH";
	private static final String ACCOUNT_ID = "valid";

//	private BackgroundCheckService backgroundCheckService = mock(BackgroundCheckService.class);
//    private ReferenceIdsManager referenceIdsManager = mock(ReferenceIdsManager.class);
//    private AccountRepository accountRepository = mock(AccountRepository.class);
//    
//    AccountOpeningService openingService;

	@BeforeEach
	public void setUp() {
//    	openingService = new AccountOpeningService(backgroundCheckService, referenceIdsManager, accountRepository,eventPublisher);
	}

	@Test
	public void testShouldOpenAccount() throws IOException {	
		
		 BackgroundCheckResults backgroundCheckResults = new BackgroundCheckResults("something_not_unacceptable", 1000);
		 when(backgroundCheckService.confirm(FIRST_NAME, LAST_NAME,TAX_ID, DOB))
		 .thenReturn(backgroundCheckResults);	
		 
		 
		 when(referenceIdsManager.obtainId(eq(FIRST_NAME), anyString(), eq(LAST_NAME), eq(TAX_ID), eq(DOB))).thenReturn(ACCOUNT_ID);
//		 when(referenceIdsManager.obtainId(any(), any(), any(), any())).thenReturn("valid");
		 
		AccountOpeningStatus accountOpeningStatus = openingService.openAccount(FIRST_NAME, LAST_NAME,TAX_ID, DOB);
		assertEquals(AccountOpeningStatus.OPENED, accountOpeningStatus);		
		verify(accountRepository, atLeastOnce()).save(ACCOUNT_ID,FIRST_NAME, LAST_NAME,TAX_ID, DOB, backgroundCheckResults);
		verify(eventPublisher).notify(ACCOUNT_ID);
	}

	@Test
	public void testShouldDeclineAccountIfUnaccptableProfileBackgroundCheckResponceReceived() throws IOException {
		when(backgroundCheckService.confirm(FIRST_NAME, LAST_NAME, TAX_ID, DOB))
				.thenReturn(new BackgroundCheckResults(UNACCEPTABLE_RISK_PROFILE, 1000));

		AccountOpeningStatus accountOpeningStatus = openingService.openAccount(FIRST_NAME, LAST_NAME, TAX_ID, DOB);
		assertEquals(AccountOpeningStatus.DECLINED, accountOpeningStatus);
	}

	@Test
	public void testShouldDeclineAccountIfNullBackgroundCheckResponceReceived() throws IOException {
		when(backgroundCheckService.confirm(FIRST_NAME, LAST_NAME, TAX_ID, DOB))
				.thenReturn(null);

		AccountOpeningStatus accountOpeningStatus = openingService.openAccount(FIRST_NAME, LAST_NAME, TAX_ID, DOB);
		assertEquals(AccountOpeningStatus.DECLINED, accountOpeningStatus);
	}
	
	
	
	
	// Testing Exceptions
	@Test
	public void testShouldThrowIfBackgroundCheckServiceThrows() throws IOException {
		when(backgroundCheckService.confirm(FIRST_NAME, LAST_NAME, TAX_ID, DOB))
				.thenThrow(new IOException());

		assertThrows(IOException.class, 
				() -> openingService.openAccount(FIRST_NAME, LAST_NAME, TAX_ID, DOB));
	}
	
	@Test
	public void testShouldThrowIfReferenceIdsManagerServiceThrows() throws IOException {
		when(backgroundCheckService.confirm(FIRST_NAME, LAST_NAME, TAX_ID, DOB))
				.thenReturn(new BackgroundCheckResults("something_acceptable", 1000));

		when(referenceIdsManager.obtainId(eq(FIRST_NAME), anyString(), eq(LAST_NAME), eq(TAX_ID), eq(DOB) ))
				.thenThrow(new RuntimeException());
		
		assertThrows(RuntimeException.class, 
				() -> openingService.openAccount(FIRST_NAME, LAST_NAME, TAX_ID, DOB));
	}
	
	@Test
	public void testShouldThrowIfAccountRepositoryServiceThrows() throws IOException {
		BackgroundCheckResults backgroundCheckResults = new BackgroundCheckResults("something_acceptable", 1000);
		
		when(backgroundCheckService.confirm(FIRST_NAME, LAST_NAME, TAX_ID, DOB))
				.thenReturn(backgroundCheckResults);

		when(referenceIdsManager.obtainId(eq(FIRST_NAME), anyString(), eq(LAST_NAME), eq(TAX_ID), eq(DOB))).thenReturn(ACCOUNT_ID);
		
		when(accountRepository.save(ACCOUNT_ID,FIRST_NAME, LAST_NAME,TAX_ID, DOB, backgroundCheckResults ))
			.thenThrow(new ArithmeticException() );
		
		assertThrows(ArithmeticException.class, 
				() -> openingService.openAccount(FIRST_NAME, LAST_NAME, TAX_ID, DOB));
	}
	
	@Test
	public void testShouldThrowIfEventPublisherServiceThrows() throws IOException {
		when(backgroundCheckService.confirm(FIRST_NAME, LAST_NAME, TAX_ID, DOB))
				.thenReturn(new BackgroundCheckResults("something_acceptable", 1000));

		when(referenceIdsManager.obtainId(eq(FIRST_NAME), anyString(), eq(LAST_NAME), eq(TAX_ID), eq(DOB))).thenReturn(ACCOUNT_ID);
		
//		when(eventPublisher.notify(ACCOUNT_ID) ).thenThrow(new RuntimeException() );
		doThrow(new RuntimeException() ).when(eventPublisher).notify(ACCOUNT_ID);
		
		assertThrows(RuntimeException.class, 
				() -> openingService.openAccount(FIRST_NAME, LAST_NAME, TAX_ID, DOB));
	}
	
	
	
	
}
