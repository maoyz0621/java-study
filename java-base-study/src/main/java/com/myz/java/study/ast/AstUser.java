/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-05-12 14:23  Inc. All rights reserved.
 */
package com.myz.java.study.ast;

// import com.myz.ast.anno.MySetter;

/**
 * mvn clean compile && java -cp target/classes/ com.myz.java.study.ast.AstUser
 * todo 未成功构建 记录
 * @author maoyz
 */
// @MySetter
public class AstUser {

    private String username;

    public AstUser(String username) {
        this.username = username;
    }

    public static void main(String[] args) {
        AstUser astUser = new AstUser("aaa");
        // System.out.println(astUser.getUsername());
    }
}