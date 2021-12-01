package com.wiredCoffee.reward;

import java.util.List;

import com.wiredCoffee.product.Product;

public class RewardByGiftService extends RewardService {
	private long giftProductId;
	

	@Override
	public RewardInformation applyReward(List<Product> order, long customerPoints) {
		RewardInformation reward = new RewardInformation();
		
		if (customerPoints >=neededPoints) {
			double price=0;
			for (Product product : order) {
				if (product.getId()== giftProductId) {
					price = product.getPrice();
				}
				
			if (price >0) {
				reward = new RewardInformation(neededPoints, price);
			}
			}
			
		}
		return reward;
	}


	public long getGiftProductId() {
		return giftProductId;
	}


	public void setGiftProductId(long giftProductId) {
		if (giftProductId > 0) {
			this.giftProductId = giftProductId;
		} else {
			throw new IllegalArgumentException("ProductId should be > 0");
		}
	}
	
	

}
