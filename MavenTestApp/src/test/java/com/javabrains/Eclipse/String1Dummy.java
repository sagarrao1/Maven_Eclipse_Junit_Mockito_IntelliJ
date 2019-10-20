package com.javabrains.Eclipse;

import static org.junit.Assert.assertEquals;

public class String1Dummy {

	private int num;
	private String name;

	public static void beforeClass() throws InterruptedException {

		System.out.println("Before class1");
		Thread.sleep(1000);

	}

	public void testLearnAssert() {

		int actualValue = Math.min(10, 6);
		int excepetN = 4;

		assertEquals(excepetN, actualValue);

	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "String1Dummy [num=" + num + ", name=" + name + "]";
	}

}
