package com.myz.spring.lifecycle;

/**
 *
 */
public class HelloWorld {
	
	public void init(){
		System.out.println("执行init()");
	}
	
	public void destory(){
		System.out.println("执行destory()");
	}

	public void defaultInit(){
		System.out.println("执行defaultInit()");
	}

	public void defaultDestory(){
		System.out.println("执行defaultDestory()");
	}

	public void say(){
		System.out.println("执行say()");
	}

}
