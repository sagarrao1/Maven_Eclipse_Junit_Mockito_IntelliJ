package com.wiredCoffee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.wiredCoffee.reward.RewardService;

interface TestHelper {

	RewardService getRewardService();
	
	 	@Test
	    @DisplayName("Correct points are set")
	    default void correctPoint() {
	        assertEquals(100, getRewardService().getNeededPoints());
	    }

}