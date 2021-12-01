package com.wiredCoffee.dynamic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

import com.wiredCoffee.product.Product;
import com.wiredCoffee.reward.RewardByGiftService;
import com.wiredCoffee.reward.RewardInformation;

public class RewardByGiftServiceDynamicTest {
	RewardByGiftService gift;

	private LongStream getStreamOfRandomNumbers() {
		Random r = new Random();		
		return r.longs(1000,2000);
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
	
	@BeforeEach
	void setup() {
//		System.out.println("Before each");
		gift = new RewardByGiftService();
		gift.setNeededPoints(100);
	}
	
//	@TestFactory
//	Collection<DynamicTest> dynamicTestFromCollection(){		
//		return Arrays.asList(
//				dynamicTest( "1st Dynamic Test",					
//						() -> assertEquals(1,1)),
//				
//				dynamicTest( "2st Dynamic Test",					
//						() -> assertEquals(1,1))
//				);
//	}

//@TestFactory
//  Stream<DynamicTest> giftProductNotInOrderRewardNotApplied() {
//  return getStreamOfRandomNumbers()
//          .limit(5)
//          .mapToObj(randomId ->
//                  dynamicTest(
//                          "Testing product ID " + randomId,
//                          () -> {
//                              gift.setGiftProductId(randomId);
//                              RewardInformation info = gift.applyReward(
//                                      getSampleOrder(), 200);
//
//                              assertEquals(0, info.getDiscount());
//                              assertEquals(0, info.getPointsRedeemed());
//                          }
//                  )
//          );
//}

@TestFactory
Stream<DynamicTest> giftProductNotInOrderRewardNotApplied() {
  Iterator<Long> inputGeneratorIterator = getStreamOfRandomNumbers().limit(5).iterator();

  Function<Long, String> displayNameGeneratorFunction = randomId -> "Testing product ID " + randomId;

  ThrowingConsumer<Long> testExecutorThrowingConsumer = randomId -> {
          gift.setGiftProductId(randomId);
          RewardInformation info = gift.applyReward(
                  getSampleOrder(), 200);

          assertEquals(0, info.getDiscount());
          assertEquals(0, info.getPointsRedeemed());
  };

  return stream(
          inputGeneratorIterator,
          displayNameGeneratorFunction,
          testExecutorThrowingConsumer
  );
}

@TestFactory
Stream<DynamicContainer> dynamicTestsWithContainers() {
  return LongStream.range(1, 6)
          .mapToObj(productId -> dynamicContainer(
                  "Container for ID " + productId,
                  Stream.of(
                          dynamicTest("Valid Id", () -> assertTrue(productId > 0)),
                          dynamicContainer("Test", Stream.of(
                              dynamicTest("Discount applied", () -> {
                                  gift.setGiftProductId(productId);
                                  RewardInformation info = gift.applyReward(
                                          getSampleOrder(), 200);

                                  assertTrue(info.getDiscount() > 0);
                              })
                          ))
                  )
          ));
}

	
}
