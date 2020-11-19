package com.myz.java.study.base.collection.list;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 根据队列Deque自定义栈
 *
 * @author maoyz on 18-3-1.
 */
public class MyStack<E> {

    /**
     * 容器
     */
    private Deque<E> container = new ArrayDeque<>();

    /**
     * 容量
     */
    private int capacity;

    public MyStack(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 压栈
     *
     * @param e
     */
    public boolean push(E e) {
        return (capacity >= this.container.size() + 1) && this.container.offerLast(e);
    }

    /**
     * 出栈并移除
     */
    public E pop() {
        return container.pollLast();
    }

    /**
     * 获取头部信息
     */
    public E peek() {
        return container.peekLast();
    }

    public int size() {
        return this.container.size();
    }

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>(4);

        stack.push("demo");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        // 无效插入
        stack.push("e");

        // 迭代输出
        while (null != stack.peek()) {
            System.out.println("栈大小：" + stack.size());
            System.out.println("栈顶元素：" + stack.peek());
            System.out.println("出栈元素：" + stack.pop());
            System.out.println("------------------");
        }
    }
}
