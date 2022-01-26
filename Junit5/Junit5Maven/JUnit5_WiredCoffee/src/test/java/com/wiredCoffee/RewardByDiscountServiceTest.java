package com.wiredCoffee;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.wiredCoffee.product.Product;
import com.wiredCoffee.reward.RewardByDiscountService;
import com.wiredCoffee.reward.RewardInformation;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RewardByDiscountServiceTest {

	List<Product> order;
	RewardByDiscountService discount;
	
	@BeforeEach
	void setup() {
//		setup order
		order = new ArrayList<>();		
		Product p1 = new Product(1,"pen",50);
		Product p2 = new Product(2,"pencil",100.3);
		Product p3 = new Product(3,"phone",5000);		
		order.add(p1);
		order.add(p2);
		order.add(p3);
		
//		setup reward
		discount= new RewardByDiscountService();
		discount.setNeededPoints(100);
		discount.setPercentage(0.1);
	}
	
	
	@Test
	@Order(1)
	void testSetPercentage() {
//		RewardByDiscountService discount= new RewardByDiscountService();
//		discount.setNeededPoints(100);		
		assertEquals(100, discount.getNeededPoints());
	}

	@Test
	@Order(2)
	void testSetNeededPoints() {
//		RewardByDiscountService discount= new RewardByDiscountService();
//		discount.setPercentage(0.1);		
		assertEquals(0.1, discount.getPercentage());
	}
	
	@Test
	void zeroCustomerPoints() {
//		RewardByDiscountService discount= new RewardByDiscountService();
//		discount.setNeededPoints(100);
//		discount.setPercentage(0.1);					
		RewardInformation applyReward = discount.applyReward(order, 2);		
		assertEquals(0, applyReward.getPointsRedeemed());
		assertEquals(0, applyReward.getDiscount());		
	}
	
	@Test
	void enoughCustomerPointsForDiscount() {
//		RewardByDiscountService discount= new RewardByDiscountService();
//		discount.setNeededPoints(100);
//		discount.setPercentage(0.1);					
		RewardInformation applyReward = discount.applyReward(order, 200);		
		
		assertAll(
			() -> assertEquals(100, applyReward.getPointsRedeemed()),
			() -> assertEquals(515.0300000000001, applyReward.getDiscount())
		 );
	}
	
	@AfterEach
	void teardown(){
		order= null;
		discount=null;
	}

}
