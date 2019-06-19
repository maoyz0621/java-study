package com.myz.java.study.design.decorator.extend;

import com.myz.java.study.design.decorator.ICar;

public class FlyCar extends SuperCar {

	public FlyCar(ICar iCar) {
		super(iCar);
	}
	
	@Override
	public String getDesc(String string) {		
		return  super.getDesc(string) + fly() ;
	}
	
	public String fly() {
		return ",能飞行";
	}

}
