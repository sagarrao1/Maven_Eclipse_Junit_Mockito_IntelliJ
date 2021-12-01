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
import com.wiredCoffee.reward.RewardByConversionService;
import com.wiredCoffee.reward.RewardInformation;

class RewardByConversionServiceTest {

	List<Product> order;
	RewardByConversionService conversion=null;
	
	@BeforeEach
	void setup() {
//		setup order	
		
//		setup reward
		conversion= new RewardByConversionService();
		conversion.setNeededPoints(100);
		conversion.setAmount(4);
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
	void testSetAmount() {
		assertEquals(4.0, conversion.getAmount());
	}

	@Test
	void testSetNeededPoints() {		
		assertEquals(100, conversion.getNeededPoints());
	}
	
	@Test
	void zeroCustomerPoints() {		
		List<Product> order = buildSampleOrder(4);
		RewardInformation applyReward = conversion.applyReward(order, 2);		
		assertEquals(0, applyReward.getPointsRedeemed());
		assertEquals(0, applyReward.getDiscount());		
	}
	
	@Test
	void enoughCustomerPointsForDiscount() {
		List<Product> order = buildSampleOrder(4);
		RewardInformation applyReward = conversion.applyReward(order, 200);
		
		assertAll(
			() -> assertEquals(100, applyReward.getPointsRedeemed()),
			() -> assertEquals(4.0, applyReward.getDiscount())
		 );
	}
	
	
	@Test
	@DisplayName("exception illegal arument with invalid product id")
	void testIllegalarumentExceptionwithInvalidId() {
		RuntimeException exception = 
				assertThrows(RuntimeException.class, () -> {conversion.setAmount(0);			
		});		
		assertEquals("Amount should be > 0", exception.getMessage());
	}

	@Test
	@DisplayName("should not exceed timeout")
	void timeOutNotExceeded() {
		int numOfrecords= 50000;
//		List<Product> order = buildSampleOrder(numOfrecords);	
		List<Product> order = buildSampleOrderLambda(numOfrecords);
//		System.out.println(order.size());		
//		conversion.setGiftProductId(numOfrecords-1);			
		
		RewardInformation reward = assertTimeout(
				Duration.ofMillis(15),				
				() -> conversion.applyReward(order, 200));
		
//		RewardInformation reward = assertTimeoutPreemptively(
//				Duration.ofNanos(5),				
//				() -> gift.applyReward(order, 200));
		
		assertEquals(4.0,reward.getDiscount());
	}
	
	@Test
    @DisplayName("Correct points are set")
    void correctPoint() {
        assertEquals(100, conversion.getNeededPoints());
    }
	
	
	@AfterEach
	void teardown(){
		order= null;
		conversion=null;
	}

}
