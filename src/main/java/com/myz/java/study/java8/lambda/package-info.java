/**
 * 1、一个 Lambda 表达式可以有零个或多个参数
 * 2、参数的类型既可以明确声明，也可以根据上下文来推断。例如：(int a)与(a)效果相同
 * 3、所有参数需包含在圆括号内，参数之间用逗号相隔。例如：(a, b) 或 (int a, int b) 或 (String a, int b, float c)
 * 4、空圆括号代表参数集为空。例如：() -> 42
 * 5、当只有一个参数，且其类型可推导时，圆括号（）可省略。例如：a -> return a*a
 * Lambda 表达式的主体可包含零条或多条语句
 * 如果 Lambda 表达式的主体只有一条语句，花括号{}可省略。匿名函数的返回类型与该主体表达式一致
 * 如果 Lambda 表达式的主体包含一条以上语句，则表达式必须包含在花括号{}中（形成代码块）。匿名函数的返回类型与代码块的返回类型一致，若没有返回则为空
 *
 * @author maoyz0621 on 19-3-24
 * @version: v1.0
 */
package com.myz.java.study.java8.lambda;