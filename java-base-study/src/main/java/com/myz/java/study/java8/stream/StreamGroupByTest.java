/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.java8.stream;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GroupBy()
 * <p>
 * 使用partitioningBy()生成的收集器，适用于将Stream中的元素依据某个二值逻辑(满足、不满足)分成互补相交的两部分，如姓名，及格与否
 * groupingBy()按照某个属性对数据分组，属性相同的元素会被对应到Map的一个key上
 *
 * @author maoyz0621 on 2019/3/18
 * @version v1.0
 */
public class StreamGroupByTest {

    @Test
    public void test() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i));
        }
        Map<Boolean, List<Student>> collect = students.stream().collect(Collectors.partitioningBy(s -> s.getGrade() > 3));
        System.out.println(collect);
    }

    @Test
    public void testGroupBy() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("A", new Department("开发")));
        employees.add(new Employee("B", new Department("开发")));
        employees.add(new Employee("C", new Department("策划")));
        employees.add(new Employee("D", new Department("策划")));
        employees.add(new Employee("E", new Department("营销")));
        //groupingBy
        Map<Department, List<Employee>> byDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(byDept);

        // 以部门名称分组
        Map<String, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName()));
        System.out.println(collect);


        // 按照部门对员工进行分组，并且只保留员工的名字
        Map<String, List<String>> collect1 = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName(), Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(collect1);
    }

}

class Student {

    private Integer grade;

    public Student() {
    }

    public Student(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class Employee {

    private Department department;

    private String name;

    public Employee(String name, Department department) {
        this.department = department;
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("department=").append(department);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

class Department {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
