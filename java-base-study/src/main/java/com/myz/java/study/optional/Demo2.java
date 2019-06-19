package com.myz.java.study.optional;

import java.util.UUID;

public class Demo2 {

	public static void main(String[] args) {
		//生成机器随机码
		String str = UUID.randomUUID().toString();
		System.out.println(str.substring(2, 7));
	}

}
