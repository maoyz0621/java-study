package com.myz.design.decorator.impl;

import com.myz.java.study.design.decorator.ICar;

/**
* 真实对象
* @author xuwt
 */
public class Car implements ICar {

	@Override
	public String getDesc(String string){
		return string;
	}


}
