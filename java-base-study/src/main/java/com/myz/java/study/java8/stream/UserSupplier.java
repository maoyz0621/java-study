package com.myz.java.study.java8.stream;

import com.myz.java.study.base.collection.domain.User;

import java.util.Random;
import java.util.function.Supplier;

/**
 * 实现Supplier接口
 * @author maoyz
 */
public class UserSupplier implements Supplier<User> {

    private int index;

    private Random random = new Random();

    @Override
    public User get() {
        return new User(random.nextInt(1000) + "", index++);
    }

}
