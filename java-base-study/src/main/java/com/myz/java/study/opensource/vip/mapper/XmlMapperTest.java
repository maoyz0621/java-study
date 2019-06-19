/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.mapper;

import com.google.common.collect.Lists;
import com.vip.vjtools.vjkit.collection.ListUtil;
import com.vip.vjtools.vjkit.mapper.XmlMapper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author maoyz0621 on 19-4-24
 * @version: v1.0
 */
public class XmlMapperTest {

    @Test
    public void objectToXml() {
        User user = new User();
        user.setId(1L);
        user.setName("calvin");
        user.setPassword("123456");

        user.getInterests().add("movie");
        user.getInterests().add("sports");

        String xml = XmlMapper.toXml(user, "UTF-8");
        // <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
        // <user uuid="1">
        //     <name>calvin</name>
        //     <interests>
        //         <interest>movie</interest>
        //         <interest>sports</interest>
        //     </interests>
        // </user>
        System.out.println("Jaxb Object to Xml result:\n" + xml);
    }

    /**
     * 测试以List对象作为根节点时的XML输出
     */
    @Test
    public void toXmlWithListAsRoot() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("calvin");
        user1.getInterests().add("movie1");
        user1.getInterests().add("sports1");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("kate");
        user2.getInterests().add("movie2");
        user2.getInterests().add("sports2");

        List<User> userList = ListUtil.newArrayList(user1, user2);

        String xml = XmlMapper.toXml(userList, "userList", User.class, "UTF-8");
        // <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
        // <userList>
        //     <user uuid="1">
        //         <name>calvin</name>
        //         <interests>
        //             <interest>movie1</interest>
        //             <interest>sports1</interest>
        //         </interests>
        //     </user>
        //     <user uuid="2">
        //         <name>kate</name>
        //         <interests>
        //             <interest>movie2</interest>
        //             <interest>sports2</interest>
        //         </interests>
        //     </user>
        // </userList>
        System.out.println("Jaxb Object List to Xml result:\n" + xml);
    }


    /**
     * 指定子节点的顺序 @XmlType
     */
    @XmlRootElement
    @XmlType(propOrder = {"name", "interests"})
    public static class User {

        private Long id;
        private String name;
        private String password;
        private List<String> interests = Lists.newArrayList();

        /**
         * 设置转换为xml节点中的属性
         */
        @XmlAttribute
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * 设置不转换为xml
         */
        @XmlTransient
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * 设置对List<String>的映射, xml为<interests><interest>movie</interest></interests>
         */
        @XmlElementWrapper(name = "interests")
        @XmlElement(name = "interest")
        public List<String> getInterests() {
            return interests;
        }

        public void setInterests(List<String> interests) {
            this.interests = interests;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
