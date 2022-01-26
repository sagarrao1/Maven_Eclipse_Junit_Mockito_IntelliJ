package com.wiredCoffee.Extn;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.wiredCoffee.reward.RewardByConversionService;

public class RewardByConversionParameterResolver implements ParameterResolver {

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		return parameterContext.getParameter().getType().equals(RewardByConversionService.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		RewardByConversionService reward = new RewardByConversionService();
		reward.setAmount(10);
		reward.setNeededPoints(100);
		return reward;
	}

}
