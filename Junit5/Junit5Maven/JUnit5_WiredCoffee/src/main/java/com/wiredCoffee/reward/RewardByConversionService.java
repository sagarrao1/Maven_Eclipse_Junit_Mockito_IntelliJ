package com.wiredCoffee.reward;

import java.util.List;

import com.wiredCoffee.product.Product;

public class RewardByConversionService extends RewardService {

	private double amount;
	
	@Override
	public RewardInformation applyReward(List<Product> order, long customerPoints) {
		RewardInformation reward = new RewardInformation();
		
		if (customerPoints >= neededPoints ) {
			double orderTotal = calculateTotal(order);
			
			if (orderTotal > amount) {
				reward= new RewardInformation(neededPoints, amount);
			}
		}
		return reward;
	}
	

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		if (amount > 0) {
			this.amount = amount;
		} else {
			throw new IllegalArgumentException("Amount should be > 0");
		}
	}

	
}
