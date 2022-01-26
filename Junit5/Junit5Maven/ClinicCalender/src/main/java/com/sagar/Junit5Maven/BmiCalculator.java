package com.sagar.Junit5Maven;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BmiCalculator {

	public static Double calculateBmi(Integer weight, Integer height) {
		Double bmi = (double) (weight * 703) / (height * height);
		Double roundedToTwoPlaces= new BigDecimal(bmi)
				.setScale(2,RoundingMode.HALF_UP).doubleValue();
		return roundedToTwoPlaces;
	}
	

}
