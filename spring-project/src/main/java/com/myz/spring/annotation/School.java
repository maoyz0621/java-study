package com.myz.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *  合格者　@Qualifier
 */
@Component
public class School {

	private String name;

	@Autowired
	@Qualifier("person")
	private Person person;

	public School() {
		System.out.println("执行School()构造");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public String toString() {
		return "School [name=" + name + "]";
	}

}
