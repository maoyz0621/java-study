package com.myz.spring.spel;

public class School {
	private String name;

	public School() {
		System.out.println("执行School()构造");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "School [name=" + name + "]";
	}

}
