package com.myz.java.study.design.decorator.impl;

import com.myz.java.study.design.decorator.ICar;

public class CarImpl2 implements ICar {

	private ICar iCar = null;

	public CarImpl2(ICar iCar) {
		this.iCar = iCar;
	}

	@Override
	public String getDesc(String string) {
		// TODO Auto-generated methodfactory stub
		return iCar.getDesc(string) + "低调奢侈";
	}

}
