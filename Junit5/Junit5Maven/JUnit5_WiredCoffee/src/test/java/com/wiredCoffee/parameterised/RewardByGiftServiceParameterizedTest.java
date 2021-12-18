package com.wiredCoffee.parameterised;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.wiredCoffee.product.Product;
import com.wiredCoffee.reward.RewardByGiftService;
import com.wiredCoffee.reward.RewardInformation;

public class RewardByGiftServiceParameterizedTest {
	RewardByGiftService gift;
	
	
	@BeforeEach
	void setup() {
		gift = new RewardByGiftService();
		gift.setNeededPoints(100);	
		System.out.println("Before each");
	}	
	
	private List<Product> getSampleOrder() {
        Product p1 = new Product(1, "Small Decaf", 1.99);
        Product p2 = new Product(2, "Big Decaf", 2.49);
        Product p3 = new Product(3, "Big Latte", 2.99);
        Product p4 = new Product(4, "Big Tea", 2.99);
        Product p5 = new Product(5, "Espresso", 2.99);
        return Arrays.asList(
        		p1, p2, p3, p4, p5);
    }

	
	@ParameterizedTest(name = "Test #{index} : productId={0}")
	@ValueSource(longs = {1,2,3,4,5})
	@DisplayName("Parameterised Test")
	void discountShouidbeApplied(long id, TestInfo testInfo, TestReporter testReporter ) {
		System.out.println("Display Name: "+ testInfo.getDisplayName());
		testReporter.publishEntry("productId",String.valueOf(id));
		
		gift.setGiftProductId(id);
		RewardInformation info = gift.applyReward(getSampleOrder(), 200);		
//		assertEquals(100, info.getPointsRedeemed());
		assertTrue(info.getDiscount() > 0);
	}
	
	
	@ParameterizedTest()
	@EnumSource(value = ProductsEnum.class)
//	@EnumSource(value = ProductsEnum.class, names= {"P1","P2"})
	@DisplayName("Parameterised Test using Enum")
	void discountShouidbeApplied(ProductsEnum product ) {		
		gift.setGiftProductId(product.getProductId());
		RewardInformation info = gift.applyReward(getSampleOrder(), 200);		
//		assertEquals(100, info.getPointsRedeemed());
		assertTrue(info.getDiscount() > 0);
	}
	
//	Parameterised test using method source
	
	private static LongStream productIds() {
		return LongStream.range(1, 5);
	}
	
	@ParameterizedTest()
	@MethodSource("productIds")
	@DisplayName("Parameterised Test using Method")
	void discountShouidbeApplied(long productId) {		
		gift.setGiftProductId(productId);
		RewardInformation info = gift.applyReward(getSampleOrder(), 200);		
//		assertEquals(100, info.getPointsRedeemed());
		assertTrue(info.getDiscount() > 0);
	}	

	
	private static Stream<Arguments> productIdsCustomerPoints(){
		return productIds()
			.mapToObj(productId -> Arguments.of(productId, 100*productId)
			);		
	}
	
	@ParameterizedTest()
	@MethodSource("productIdsCustomerPoints")
	@DisplayName("Parameterised Test using Method with 2 arguments")
	void discountShouidbeApplied(long productId, long customerPoints) {		
		gift.setGiftProductId(productId);
		RewardInformation info = gift.applyReward(getSampleOrder(), customerPoints);		
//		assertEquals(100, info.getPointsRedeemed());
		assertTrue(info.getDiscount() > 0);
	}

	
	@ParameterizedTest()
	@CsvFileSource(resources = "/product-point-data.csv")
	@DisplayName("Parameterised Test using csv file source")
	void discountShouidbeAppliedCsvFileSource(long productId, long customerPoints) {		
		gift.setGiftProductId(productId);
		RewardInformation info = gift.applyReward(getSampleOrder(), customerPoints);		
//		assertEquals(100, info.getPointsRedeemed());
		assertTrue(info.getDiscount() > 0);
	}
	
//	customr argumnt provider using new class
	@ParameterizedTest()
	@ArgumentsSource(ProductIdArgumentProvider.class)
	@DisplayName("Parameterised Test using csv file source")
	void discountShouidbeAppliedCustomProvder(long productId, long customerPoints) {		
		gift.setGiftProductId(productId);
		RewardInformation info = gift.applyReward(getSampleOrder(), customerPoints);		
//		assertEquals(100, info.getPointsRedeemed());
		assertTrue(info.getDiscount() > 0);
	}
}