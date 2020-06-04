package com.myz.spring.autowired;

import java.util.List;
import java.util.Set;

public class Person {
	private int age;
	private String name;
	private List<School> schools;
	private Set<Teacher> teachers;

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

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}
	
	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + ", schools=" + schools + ", teachers=" + teachers + "]";
	}

}
