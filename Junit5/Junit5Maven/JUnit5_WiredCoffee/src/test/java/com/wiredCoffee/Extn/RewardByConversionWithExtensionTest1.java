package com.wiredCoffee.Extn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import com.wiredCoffee.reward.RewardByConversionService;
import com.wiredCoffee.reward.RewardInformation;


@ExtendWith(LifeCycleExtension.class)
public class RewardByConversionWithExtensionTest1 {
	
	
	private RewardByConversionService reward;

	@BeforeAll
	static void setupAll(){
		System.out.println("BeforeAll");
	}
		
	@BeforeEach
	void setup() {
		System.out.println("Before Each");
		reward= new RewardByConversionService();
		reward.setNeededPoints(100);
		reward.setAmount(10);
		
	}
	
	@Test
	@ExtendWith(LifeCycleExtension.class)
	void changeAmt() {
		reward.setAmount(20);
		System.out.println("change amount");
		assertEquals(20, reward.getAmount());
	}
	
	
	@Test
	void rewardNotApplied() {
		reward.setAmount(20);
		RewardInformation info = reward.applyReward(new ArrayList<>(), 0);
		System.out.println("rewardNotApplied");
		assertEquals(0, info.getDiscount());
	}
	
	
	@AfterEach
	void teardown() {
		System.out.println("After each");
	}
	
	@AfterAll
	static void teardownAll() {
		System.out.println("After all");
	}
}
