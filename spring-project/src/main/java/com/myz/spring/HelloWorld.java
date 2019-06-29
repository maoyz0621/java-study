package com.myz.spring;

/**
 *
 */
public class HelloWorld {
	private String name;

	public HelloWorld() {
		System.out.println("执行HelloWorld()无参构造");
	}

	public HelloWorld(String name) {
		this.name = name;
		System.out.println("执行HelloWorld()有参构造");
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("执行setter():" + this.name);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "HelloWorld [name=" + name + "]";
	}
	
	public void init(){
		System.out.println("执行init()");
	}
	
	public void destory(){
		System.out.println("执行destory()");
	}

}
