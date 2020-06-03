package com.myz.java.study.annotation;

@MyAnnotation(value = {"a1", "a2"}, age = 12)
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @MyAnnotation(value = "b1", age = 16)
    public void setName(String name) {
        this.name = name;
    }

}
