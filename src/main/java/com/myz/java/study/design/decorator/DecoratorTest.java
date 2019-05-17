package com.myz.java.study.design.decorator;

import com.myz.java.study.design.decorator.extend.SuperCar;
import com.myz.java.study.design.decorator.impl.Car;
import com.myz.java.study.design.decorator.impl.CarImpl1;
import com.myz.java.study.design.decorator.impl.CarImpl2;

class DecoratorTest {
    /**
     *  * 第二种装饰者模式
     * 所有类都继承基类,除了实体类不实现构造方法
     */
	public static void main(String[] args) {
		ICar iCar1 = new SuperCar(new Car());
		System.out.println(iCar1.getDesc("宝马"));

		ICar iCar2 = new SuperCar(new CarImpl1(new  Car()));
		System.out.println(iCar2.getDesc("宝马"));

		ICar iCar3 = new SuperCar(new CarImpl1(new CarImpl2(new Car())));
		System.out.println(iCar3.getDesc("宝马"));
		
	}				
}
