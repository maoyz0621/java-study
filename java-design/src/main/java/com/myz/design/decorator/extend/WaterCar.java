package com.myz.design.decorator.extend;

import com.myz.java.study.design.decorator.ICar;

public class WaterCar extends SuperCar {

	public WaterCar(ICar iCar) {
		super(iCar);
	}
	
	@Override
	public String getDesc(String string)  {
		return  super.getDesc(string) + water();
	}
	
	private String water() {
		return ",能潜水";		
	}

}
