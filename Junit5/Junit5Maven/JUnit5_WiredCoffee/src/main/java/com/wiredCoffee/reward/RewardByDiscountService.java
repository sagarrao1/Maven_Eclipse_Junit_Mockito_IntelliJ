package com.wiredCoffee.reward;

import java.util.List;

import com.wiredCoffee.product.Product;

public class RewardByDiscountService extends RewardService {
	
	private double percentage;

	@Override
	public RewardInformation applyReward(List<Product> order, long customerPoints) {
		RewardInformation reward = new RewardInformation();
		
		if (customerPoints >= neededPoints) {
			double orderTotal=calculateTotal(order);
			double discount = orderTotal*percentage;
			reward = new RewardInformation(neededPoints, discount);
		}
		return reward;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		 if(percentage > 0) {
	            this.percentage = percentage;
	        } else {
	            throw new IllegalArgumentException("Percentage should be greater than zero");
	        }
	}

	
}
