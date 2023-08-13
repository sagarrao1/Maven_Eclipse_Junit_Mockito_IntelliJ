package com.learining.JunitMockito;

public class Calculator {
	
	CalculatorService service;	
	
	
	public Calculator(CalculatorService service) {		
		this.service = service;
	}



	public int perform(int i, int j) {  // (i+j) * 1
		return service.add(i, j)*i;
//		return (i+j)*i;
	}

}
