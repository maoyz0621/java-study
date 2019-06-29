package com.myz.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("person")
public class PersonImp implements Person {

	@Autowired
	private School school;

	@Override
	public void eat() {
		System.out.println("吃饭");
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public School getSchool() {
		return school;
	}

	@Override
	public String toString() {
		return "PersonImp [school=" + school + "]";
	}

}
