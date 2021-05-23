/**
 * Copyright 2021 Inc.
 **/
package com.myz.java.study.juc.atomic.aba;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * 带标记的原子引用类型。
 *
 * @author maoyz0621 on 2021/5/19
 * @version v1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AtomicMarkableReferenceUser {
    private int age;
    private String userName;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        AtomicMarkableReferenceUser user1 = new AtomicMarkableReferenceUser(12, "aaa");
        AtomicMarkableReferenceUser user2 = new AtomicMarkableReferenceUser(22, "bbb");
        AtomicMarkableReference<AtomicMarkableReferenceUser> markableReference = new AtomicMarkableReference(user1, true);
    }
}