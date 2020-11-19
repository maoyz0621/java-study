/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.vip.mapper;

import com.google.common.collect.Lists;
import com.vip.vjtools.vjkit.collection.ListUtil;
import com.vip.vjtools.vjkit.mapper.BeanMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author maoyz0621 on 19-4-23
 * @version: v1.0
 */
public class BeanMapperTest {

    @Test
    public void copySingleObject() {
        Student student = new Student("zhang3", 20, new Teacher("li4"), ListUtil.newArrayList("chinese", "english"));
        StudentVO studentVO = BeanMapper.map(student, StudentVO.class);
        System.out.println(studentVO);
    }

    @Test
    public void copyListObject() {
        Student student1 = new Student("zhang3", 20, new Teacher("li4"), ListUtil.newArrayList("chinese", "english"));
        Student student2 = new Student("zhang4", 30, new Teacher("li5"), ListUtil.newArrayList("chinese2", "english4"));
        Student student3 = new Student("zhang5", 40, new Teacher("li6"), ListUtil.newArrayList("chinese3", "english5"));
        List<Student> studentList = ListUtil.newArrayList(student1, student2, student3);

        List<StudentVO> studentVoList = BeanMapper.mapList(studentList, StudentVO.class);
        System.out.println(studentVoList.get(0));
        System.out.println(studentVoList.get(1));
        System.out.println(studentVoList.get(2));
    }

    @Test
    public void copyArrayObject() {
        Student student1 = new Student("zhang3", 20, new Teacher("li4"), ListUtil.newArrayList("chinese", "english"));
        Student student2 = new Student("zhang4", 30, new Teacher("li5"), ListUtil.newArrayList("chinese2", "english4"));
        Student student3 = new Student("zhang5", 40, new Teacher("li6"), ListUtil.newArrayList("chinese3", "english5"));
        Student[] studentList = new Student[]{student1, student2, student3};

        StudentVO[] studentVOS = BeanMapper.mapArray(studentList, StudentVO.class);
        System.out.println(studentVOS[0]);
        System.out.println(studentVOS[1]);
        System.out.println(studentVOS[2]);
    }

    @Test
    public void copy2Map() {
        Teacher teacher = new Teacher("zhang");
        Map map1 = BeanMapper.map(teacher, Map.class);
        System.out.println(map1);

        Student student = new Student("zhang3", 20, new Teacher("li4"), ListUtil.newArrayList("chinese", "english"));
        Map map = BeanMapper.map(student, Map.class);
        System.out.println(map.get("teacher").toString());
        System.out.println(map.get("name"));
        System.out.println(map.get("course"));
        System.out.println(map.get("age"));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Student {
        private String name;
        private Integer age;
        private Teacher teacher;
        private List<String> course = Lists.newArrayList();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Teacher {
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentVO {
        private String name;
        private Integer age;
        private TeacherVO teacher;
        private List<String> course = Lists.newArrayList();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeacherVO {
        private String name;
    }
}
