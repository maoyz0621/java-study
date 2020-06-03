package com.myz.spring.aop.xml;

public class CalculatorImp implements ICalculator {

	@Override
	public int add(int x, int y) {
		System.out.println("add()");
		return x + y;

	}

	@Override
	public float divide(int x, int y) {
		System.out.println("divide()");
		return x / y;
	}

}
