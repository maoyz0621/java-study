package com.myz.java.study.base.collection.domain;

/**
 * @author maoyz on 18-3-1.
 */
public class Person implements Comparable<Person> {

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 要求：
     * 优先年龄排序：从大到小
     * 按姓氏排序，倒序
     * 返回值 -1, 1, 0
     */
    @Override
    public int compareTo(Person o) {
        // 优先年龄排序
        if (this.age > o.age) {
            return -1;
        } else if (this.age < o.age) {
            return 1;
        } else {
            // 按姓氏排序
            return -this.name.compareTo(o.name);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
