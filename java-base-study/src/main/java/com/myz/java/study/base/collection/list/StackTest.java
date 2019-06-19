package com.myz.java.study.base.collection.list;

import org.junit.Test;

import java.util.Stack;

/**
 * @author maoyz on 18-3-1.
 */
public class StackTest {

    /**
     * Stack:先进后出
     * push(E) 把项压入堆栈顶部
     * pop() 移除堆栈顶部的对象，并作为此函数的值返回该对象。
     */
    @Test
    public void testStack() {
        Stack<String> stack = new Stack<>();
        // push(e)入栈
        stack.push("demo");
        stack.push("b");
        stack.push("c");
        System.out.println(stack);

        int j = 0;
        int count = stack.size();
        for (int i = 0; i < count; i++) {
            j++;
            // pop()出栈
            System.out.println((i + 1) + " --> " + stack.pop());
        }
        System.out.println(j);
    }

}
