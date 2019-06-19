package com.myz.java.study.base.collection;

/**
 * 重写：
 * hashcode()
 * equals()
 *
 * @author maoyz on 18-3-1.
 */
public class Emp {

    private final String name;
    private final int age;

    public Emp() {
        name = null;
        age = 0;
    }

    public Emp(int age, String name) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        // 自身比较
        if (this == o) {
            return true;
        }
        // null比较
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        // 类型转换
        Emp emp = (Emp) o;

        if (age != emp.age) {
            return false;
        }
        return name.equals(emp.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }
}
