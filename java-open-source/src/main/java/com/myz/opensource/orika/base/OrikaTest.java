/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.orika.base;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;

import java.util.Date;

/**
 * 我们可以选择BoundMapperFacade 代替缺省性能较慢的 MapperFacade 类
 *
 * @author maoyz0621 on 19-5-12
 * @version: v1.0
 */
public class OrikaTest {

    // 创建MapperFactory  .mapNulls(false) null值不会被映射 全局配置
    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();

    @Test
    public void test0() {
        mapperFactory.classMap(StudentVo.class, StudentBo.class);
        MapperFacade facade = mapperFactory.getMapperFacade();
        StudentVo studentVo = new StudentVo();
        studentVo.setName(null);
        studentVo.setAge(12);
        StudentBo studentBo = facade.map(studentVo, StudentBo.class);
        System.out.println(studentBo);
    }

    /**
     * 基本Bean映射   MapperFacade
     * <p>
     * mapperFactory.classMap(StudentVo.class, StudentBo.class);
     * MapperFacade facade = mapperFactory.getMapperFacade();
     */
    @Test
    public void test() {
        mapperFactory.classMap(StudentVo.class, StudentBo.class);
        MapperFacade facade = mapperFactory.getMapperFacade();
        StudentVo studentVo = new StudentVo();
        studentVo.setName("aaaa");
        studentVo.setAge(12);
        StudentBo studentBo = facade.map(studentVo, StudentBo.class);
        System.out.println(studentBo);
    }

    /**
     * 基本Bean映射   BoundMapperFacade
     * <p>
     * BoundMapperFacade<StudentVo, StudentBo> facade = mapperFactory.getMapperFacade(StudentVo.class, StudentBo.class, true);
     */
    @Test
    public void test1() {
        BoundMapperFacade<StudentVo, StudentBo> facade = mapperFactory.getMapperFacade(StudentVo.class, StudentBo.class, true);
        StudentVo studentVo = new StudentVo();
        studentVo.setName("aaaa");
        studentVo.setAge(12);
        studentVo.setBirth(new Date());
        StudentBo studentBo = facade.map(studentVo);
        System.out.println(studentBo);
    }

    /**
     * 对于BoundMapperFacade的双向映射，我们必须明确地调用mapReverse方法，而不是我们在默认MapperFacade中看到的map方法
     */
    @Test
    public void test2() {
        BoundMapperFacade<StudentVo, StudentBo> facade = mapperFactory.getMapperFacade(StudentVo.class, StudentBo.class);
        StudentBo studentBo = new StudentBo();
        studentBo.setName("aaaa");
        studentBo.setAge(12);
        StudentVo studentVo = facade.mapReverse(studentBo);
        System.out.println(studentVo);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 注册字段  .field()    .register()
     */
    @Test
    public void test3() {
        mapperFactory.classMap(PersonB.class, PersonA.class)
                .field("nom", "name")
                .field("surnom", "nickname")
                .field("age", "age")
                .register();
        MapperFacade facade = mapperFactory.getMapperFacade();
        PersonA personA = new PersonA("a", "b", 12);
        PersonB personB = facade.map(personA, PersonB.class);
        System.out.println(personB);
    }

    /**
     * 排除字段  .exclude()   .field()    .register()
     */
    @Test
    public void test4() {
        mapperFactory.classMap(PersonB.class, PersonA.class)
                .exclude("nom")
                .field("surnom", "nickname")
                .field("age", "age")
                .register();
        MapperFacade facade = mapperFactory.getMapperFacade();
        PersonA personA = new PersonA("a", "b", 12);
        PersonB personB = facade.map(personA, PersonB.class);
        System.out.println(personB);
    }


}
