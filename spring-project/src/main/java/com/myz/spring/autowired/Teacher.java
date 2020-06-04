package com.myz.spring.autowired;

public class Teacher {
	private String name;

	public Teacher() {
		System.out.println("执行Teacher()构造");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Teacher [name=" + name + "]";
	}

}
