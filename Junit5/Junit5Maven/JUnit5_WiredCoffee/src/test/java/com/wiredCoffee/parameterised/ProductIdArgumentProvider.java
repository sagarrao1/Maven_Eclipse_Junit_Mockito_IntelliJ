package com.wiredCoffee.parameterised;

import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ProductIdArgumentProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return LongStream.range(1, 6)
				.mapToObj(
						id -> Arguments.of(id , 200*id)
						);
	}

}
