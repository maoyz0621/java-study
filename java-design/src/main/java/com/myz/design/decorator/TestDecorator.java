package com.myz.design.decorator;

import com.myz.java.study.design.decorator.extend.FlyCar;
import com.myz.java.study.design.decorator.extend.SuperCar;
import com.myz.java.study.design.decorator.extend.WaterCar;
import com.myz.java.study.design.decorator.impl.Car;

class TestDecorator {

    /**
     * 第一种装饰者模式
     * 只有实体类和装饰者继承基类
     * 其它都继承装饰者
     */
	public static void main(String[] args) {
		ICar iCar = new SuperCar(new Car());
		System.out.println(iCar.getDesc("宝马"));

		ICar iCar1 = new SuperCar(new WaterCar(new Car()));
		System.out.println(iCar1.getDesc("宝马"));

		ICar iCar2 = new SuperCar(new FlyCar(new WaterCar(new Car())));
		System.out.println(iCar2.getDesc("宝马"));

	}				
}
