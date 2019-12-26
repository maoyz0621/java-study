package com.myz.design.decorator.impl;

import com.myz.java.study.design.decorator.ICar;

public class CarImpl1 implements ICar {

	private ICar iCar = null;
	
	public CarImpl1(ICar iCar) {
		this.iCar = iCar;
	}

	@Override
	public String getDesc(String string){
		// TODO Auto-generated methodfactory stub
		return iCar.getDesc(string) + "内饰豪华";
	}

}
