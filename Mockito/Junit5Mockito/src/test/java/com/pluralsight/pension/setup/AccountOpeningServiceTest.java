package com.pluralsight.pension.setup;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
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
		
//		verify(accountRepository, atLeastOnce()).save(ACCOUNT_ID,FIRST_NAME, LAST_NAME,TAX_ID, DOB, backgroundCheckResults);
		
//		ArgumentCaptor example to capture values from from method arguments
//		when we use captor matcher, we need to use all other aruments ar matcher, for that reason we use eq()
//		Argument captor will used in verify method
		ArgumentCaptor<BackgroundCheckResults> backgroundResultCaptor = 
										ArgumentCaptor.forClass(BackgroundCheckResults.class);		
		verify(accountRepository, atLeastOnce())
				.save(eq(ACCOUNT_ID),eq(FIRST_NAME), eq(LAST_NAME),eq(TAX_ID), eq(DOB),backgroundResultCaptor.capture());
		
		System.out.println(backgroundResultCaptor.getValue().getRiskProfile() + "\n" +
						   backgroundResultCaptor.getValue().getUpperAccountLimit() );
		
		assertEquals(backgroundCheckResults.getRiskProfile(), backgroundResultCaptor.getValue().getRiskProfile() );
		assertEquals(backgroundCheckResults.getUpperAccountLimit(), backgroundResultCaptor.getValue().getUpperAccountLimit() );
//		ArgumentCaptor end
		
		verify(eventPublisher).notify(ACCOUNT_ID);
		verify(eventPublisher, atLeastOnce()).notify(ACCOUNT_ID);
		
//		we can use argument matchers in verify also when we can pass anything
		verify(eventPublisher, atLeastOnce()).notify(anyString());
		
		
//		stub and mock are Same we use backgroundCheckService as stub above, so we should say ignore stubs		
		verifyNoMoreInteractions( ignoreStubs( backgroundCheckService, referenceIdsManager) );		
		verifyNoMoreInteractions( accountRepository, eventPublisher );
		
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
	
	
	
//	BDD STYLE test case	
	
	@Test
	public void testShouldOpenAccountBDD_Style() throws IOException {	
		
		 BackgroundCheckResults backgroundCheckResults = new BackgroundCheckResults("something_not_unacceptable", 1000);
		 BDDMockito.given(backgroundCheckService.confirm(FIRST_NAME, LAST_NAME,TAX_ID, DOB))
		 .willReturn(backgroundCheckResults);	
		 
		 
		 BDDMockito.given(referenceIdsManager.obtainId(eq(FIRST_NAME), anyString(), eq(LAST_NAME), eq(TAX_ID), eq(DOB)))
		 .willReturn(ACCOUNT_ID);
//		 when(referenceIdsManager.obtainId(any(), any(), any(), any())).thenReturn("valid");
		 
		AccountOpeningStatus accountOpeningStatus = openingService.openAccount(FIRST_NAME, LAST_NAME,TAX_ID, DOB);
		assertEquals(AccountOpeningStatus.OPENED, accountOpeningStatus);	
		
//		verify(accountRepository, atLeastOnce()).save(ACCOUNT_ID,FIRST_NAME, LAST_NAME,TAX_ID, DOB, backgroundCheckResults);
		
//		ArgumentCaptor example to capture values from from method arguments
//		when we use captor matcher, we need to use all other aruments ar matcher, for that reason we use eq()
//		Argument captor will used in verify method
		ArgumentCaptor<BackgroundCheckResults> backgroundResultCaptor = 
										ArgumentCaptor.forClass(BackgroundCheckResults.class);	
		
		BDDMockito.then(accountRepository).
				should().save(eq(ACCOUNT_ID),eq(FIRST_NAME), eq(LAST_NAME),eq(TAX_ID), eq(DOB),backgroundResultCaptor.capture());
		
		System.out.println(backgroundResultCaptor.getValue().getRiskProfile() + "\n" +
						   backgroundResultCaptor.getValue().getUpperAccountLimit() );
		
		assertEquals(backgroundCheckResults.getRiskProfile(), backgroundResultCaptor.getValue().getRiskProfile() );
		assertEquals(backgroundCheckResults.getUpperAccountLimit(), backgroundResultCaptor.getValue().getUpperAccountLimit() );
//		ArgumentCaptor end
		
		BDDMockito.then(eventPublisher).should().notify(ACCOUNT_ID);
		
//		we can use argument matchers in verify also when we can pass anything
		verify(eventPublisher, atLeastOnce()).notify(anyString());
		
		
//		stub and mock are Same we use backgroundCheckService as stub above, so we should say ignore stubs		
		verifyNoMoreInteractions( ignoreStubs( backgroundCheckService, referenceIdsManager) );		
		verifyNoMoreInteractions( accountRepository, eventPublisher );
		
//		BDDMockito.then(accountRepository).shouldHaveNoInteractions();
//		BDDMockito.then(eventPublisher).shouldHaveNoInteractions();
		
	}	
	
	
	
}
