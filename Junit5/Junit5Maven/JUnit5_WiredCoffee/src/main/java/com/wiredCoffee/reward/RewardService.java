package com.wiredCoffee.reward;
import java.util.List;
import com.wiredCoffee.product.Product;

public abstract class RewardService {
	
	protected long neededPoints;	

	protected double calculateTotal(List<Product> products) {
		double sum=0;
		for (Product product : products) {
			sum +=product.getPrice();
		}		
		return sum;
	}
	
	public abstract RewardInformation applyReward(
			List<Product> order, long customerPoints );

	public long getNeededPoints() {
		return neededPoints;
	}

	public void setNeededPoints(long neededPoints) {
		this.neededPoints = neededPoints;
	}
	
	
}
