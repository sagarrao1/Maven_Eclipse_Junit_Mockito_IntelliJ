package io.javabrain;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

/*default id PER_METHOD and we need to use static for @beforeall method*/
//@TestInstance(Lifecycle.PER_CLASS) 
class MathUtilTest {


	/*
	 * using dependency injecttion we can use Testinfo and TestReport these are
	 * interfaces, when we then in our code dynamic implemtation is applied
	 */
	
	private MathUtil math;
	private TestInfo testInfo; 
	TestReporter testReporter;
	
   @BeforeAll
   static void beforeAllInit() {
	   System.out.println("this needs to run before all ...");
   }


	@BeforeEach
	void initEach(TestInfo testInfo, TestReporter testReporter) {		
		math = new MathUtil();
		this.testInfo=testInfo;
		this.testReporter=testReporter;
	}
	
	@AfterEach
	void teardown() {
//		System.out.println("cleaning up.. after each method ..");
	}

	@Nested
	@Tag("Math")
	class AddTest {
		@Test
		void testAddingTwoPositives() {
			assertEquals(2, math.add(1, 1), 
					"Add method should return the sum of two numbers");
		}
		
		@Test
		void testAddingTwoNegatives() {
			assertEquals(-2, math.add(-1, -1), 
					"Add method should return the sum of two numbers");
		}
		
		
		/*
		 * if necessary, the failure message will be retrieved lazily from the supplied
		 * {@code messageSupplier}. Using supplier for assert messages it will prepare
		 * message string only when there is failure in this lazy assert nessage in
		 * normal assert messages it will prepare message irrespective of fail or pass
		 * of test case it is used for performce imrpovement
		 */		
		
		@Test
		@DisplayName("WhenAddingAPositiveAndANegative")
		void testAddingAPositiveAndANegative() {
			int expected=0;
			int actual=math.add(-1, 1);
			assertEquals(expected, actual, 
					() -> "Add method should return: "+ expected +" but returned : "+ actual);
		}
	}
	
	@Test 
	@Tag("multiply")
	@DisplayName("testing  multiply method")
	void testMultiply() {
		
//		System.out.println("Running "+ testInfo.getDisplayName()+ " with tags "+ testInfo.getTags());
		testReporter.publishEntry("Running "+ testInfo.getDisplayName()+ " with tags "+ testInfo.getTags());
		assertAll(
				() -> assertEquals(0, math.multiply(1, 0)),
				() -> assertEquals(1, math.multiply(1, 1)),
				() -> assertEquals(6, math.multiply(2, 3))
				);
	}
		
	@Test 
	void computeCircleArea() {
		assertEquals(314.1592653589793, math.computeCircleArea(10), 
				"Should return right circle area");
	}
	
	
	/*
	 * assumption like when server is up then only run this test case or when DB is
	 * up then only run this test cases otherwise it will fail anyway
	 * you want to run this test case when is up only
	 */ 
	
	@Test
	@DisplayName("Testing testDevide using assumptions")
	void testDivide() {	
		boolean value= true;
		assumeTrue(value);
		assertThrows(ArithmeticException.class, () -> math.devide(1, 0), 
				"Divide should throw ArithmeticException when denominator is zero");
	}
	
	
	
	
	@Test
//	@EnabledOnOs(OS.LINUX)
//	@EnabledOnJre(JRE.JAVA_10)
	void testAdd() {
		MathUtil math= new MathUtil();		
		assertEquals(5, math.add(2, 3));
	}

	
	@Test
	@Tag("Math")
	void testDevide() {
		MathUtil math= new MathUtil();
		assertThrows(ArithmeticException.class, () -> math.devide(1, 0));
	}
	
	
//	@Disabled
	@RepeatedTest(3)
	void testAddNegativenum( RepetitionInfo repetitionInfo ) {		
		if (repetitionInfo.getCurrentRepetition()==2) {
//			do something
		}
		MathUtil math= new MathUtil();		
		assertEquals(2, math.add(-1, 3));
	}	
}
