package com.myz.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author maoyz0621
 */
@Component("person")
public class PersonImp implements Person {

    @Autowired
    private School school;

    public PersonImp() {
        System.out.println("执行PersonImp()构造");
    }

    @Override
    public void eat() {
        System.out.println("吃饭");
        System.out.println("school=" + school);
    }

    // public void setSchool(School school) {
    //     this.school = school;
    // }

    public School getSchool() {
        return school;
    }

    @Override
    public String toString() {
        return "PersonImp [school=" + school + "]";
    }

}
