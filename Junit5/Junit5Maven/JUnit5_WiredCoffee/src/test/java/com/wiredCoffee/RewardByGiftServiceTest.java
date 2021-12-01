package com.wiredCoffee;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.wiredCoffee.product.Product;
import com.wiredCoffee.reward.RewardByGiftService;
import com.wiredCoffee.reward.RewardInformation;

class RewardByGiftServiceTest {

	List<Product> order;
	RewardByGiftService gift=null;
	
	@BeforeEach
	void setup() {
//		setup order	
		
//		setup reward
		gift= new RewardByGiftService();
		gift.setNeededPoints(100);
		gift.setGiftProductId(4);
	}
	
	private List<Product> buildSampleOrder(int numOfProducts){		
		order = new ArrayList<>();		
		for (int i=1;i<=numOfProducts; i++) {			
			Product p = new Product(i,"product "+i, 2.99);	
			order.add(p);
		}
		return order;
	}
	
	private List<Product> buildSampleOrderLambda(int numOfProducts){		
		return IntStream.range(1, numOfProducts)
				.mapToObj(i -> new Product(i,"Product "+i, 2.99))
				.collect(Collectors.toList());
	}
	
	@Test
	void testSetProductId() {
		assertEquals(4, gift.getGiftProductId());
	}

	@Test
	void testSetNeededPoints() {		
		assertEquals(100, gift.getNeededPoints());
	}
	
	@Test
	void zeroCustomerPoints() {		
		List<Product> order = buildSampleOrder(4);
		RewardInformation applyReward = gift.applyReward(order, 2);		
		assertEquals(0, applyReward.getPointsRedeemed());
		assertEquals(0, applyReward.getDiscount());		
	}
	
	@Test
	void enoughCustomerPointsForDiscount() {
		List<Product> order = buildSampleOrder(4);
		RewardInformation applyReward = gift.applyReward(order, 200);
		
		assertAll(
			() -> assertEquals(100, applyReward.getPointsRedeemed()),
			() -> assertEquals(2.99, applyReward.getDiscount())
		 );
	}
	
	
	@Test
	@DisplayName("exception illegal arument with invalid product id")
	void testIllegalarumentExceptionwithInvalidId() {
		long productId=0;
		RuntimeException exception = 
				assertThrows(RuntimeException.class, () -> {gift.setGiftProductId(productId);			
		});		
		assertEquals("ProductId should be > 0", exception.getMessage());
		assertTrue(exception.getMessage().contains(String.valueOf(productId)));
	}

	@Test
	@DisplayName("should not exceed timeout")
	void timeOutNotExceeded() {
		int numOfrecords= 50000;
//		List<Product> order = buildSampleOrder(numOfrecords);	
		List<Product> order = buildSampleOrderLambda(numOfrecords);
//		System.out.println(order.size());		
		gift.setGiftProductId(numOfrecords-1);			
		
		RewardInformation reward = assertTimeout(
				Duration.ofMillis(15),				
				() -> gift.applyReward(order, 200));
		
//		RewardInformation reward = assertTimeoutPreemptively(
//				Duration.ofNanos(5),				
//				() -> gift.applyReward(order, 200));
		
		assertEquals(2.99,reward.getDiscount());
	}
	
	
	
	
	@AfterEach
	void teardown(){
		order= null;
		gift=null;
	}

}
