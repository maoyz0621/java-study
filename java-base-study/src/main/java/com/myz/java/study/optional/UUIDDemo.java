package com.myz.java.study.optional;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class UUIDDemo {

	public static void main(String[] args) {
		//生成机器随机码
		String str = UUID.randomUUID().toString();
		System.out.println(str.substring(2, 7));

        IntStream ints = ThreadLocalRandom.current().ints();
	}

}
