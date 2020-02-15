package com.myz.java.study.optional;

public class Demo1 {
	
	public static void main(String[] args) {
		int i1 = 1;
		int i2 = 3;
		float f1 = 1f;
		float f2 = 3;
		int result1,result2;
		float result3,result4;
		result1 = i1/i2;
//		result2 = f1/f2;	//float ->int
		result3 = i1/i2;
		result4 = f1/f2;			
		System.out.println(result1);	//0
		System.out.println(result3);	//0.0
		System.out.println(result4);	//0.3333333334
		
		/**
		 * char类型
		 * 单引号
		 * */
		char c1 = 'a';
		char c2 = 'a'+1;
		char c3 = 'a'-2;		
		System.out.println(c1);		//demo
		System.out.println(c2);		//b
		System.out.println(c3);		//-
		
		/*
		 * 常量final修饰,习惯大写,数值不能改变
		 * 变量可以改变
		 * */
		
		@SuppressWarnings("unused")
		final float A;
		A = 3.4f;
//		ThreadUseObject = 3.5f;
		@SuppressWarnings("unused")
		float b;
		b = 3.4f;
		b = 3.5f;
		
		
		/*
		 * 数据类型转换
		 * 一种自动转换
		 * 一中强制转换
		 * */
		
		//自动转换
		short s = 23;
		int i = s;
		System.out.println(i);
		//强制转换
		long l = 1000000L;
		int i_1 = (int) l;
		float f  = 1.333f;
		int i_2 = (int) f;
		
		System.out.println(i_1);
		System.out.println(i_2);   	//1

		}
}

