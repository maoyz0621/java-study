package com.myz.spring.property;

public class Person {
	private int age;
	private String username;

	public Person() {
	}

	public int getAge() {
		return age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", username=" + username + "]";
	}

}
