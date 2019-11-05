/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.juc.atomic.aba;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 对某个类进行原子引用 AtomicReference
 *
 * @author maoyz0621 on 19-11-5
 * @version: v1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AtomicReferenceUser {

    private Integer age;

    private String userName;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        AtomicReferenceUser user1 = new AtomicReferenceUser(12, "aaa");
        AtomicReferenceUser user2 = new AtomicReferenceUser(22, "bbb");
        AtomicReference<AtomicReferenceUser> reference = new AtomicReference<>(user1);

        // true{"age":22,"userName":"bbb"}
        System.out.println(reference.compareAndSet(user1, user2) + reference.get().toString());

        // false{"age":22,"userName":"bbb"}
        System.out.println(reference.compareAndSet(user1, new AtomicReferenceUser(33, "ccc")) + reference.get().toString());
    }
}
