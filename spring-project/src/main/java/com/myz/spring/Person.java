package com.myz.spring;

public class Person {
	private int age;
	private String name;
	private School school ; 

	public Person() {
		System.out.println("执行Person()构造");
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + ", school=" + school + "]";
	}

}
