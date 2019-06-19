package com.myz.java.study.base.collection.list;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 自定义LinkedList链表
 *
 * @author maoyz on 18-2-19.
 */
public class MyLinkedList<E> implements List<E>, Serializable {
    private static final Logger logger = LoggerFactory.getLogger(MyLinkedList.class);
    private static final long serialVersionUID = -1864091590665105624L;

    /**
     * 第一个节点Node，为了查询
     * transient 非序列化关键字
     * <p>
     * (first == null && last == null) ||
     * (first.prev == null && first.item != null)
     */
    transient Node<E> first;

    /**
     * 最后一个节点Node，为了添加
     * <p>
     * (first == null && last == null) ||
     * (last.next == null && last.item != null)
     */
    transient Node<E> last;

    /**
     * 大小
     */
    transient int size;

    public MyLinkedList() {
    }

    /**
     * 添加
     */
    @Override
    public boolean add(E e) {
        // 新建Node
        Node<E> node = new Node<E>();

        // 当第一个Node是null时，此时结构:  null - e - null
        if (first == null) {
            node.prev = null;
            node.item = e;
            node.next = null;

            // 此时的Node既是first，也是last
            first = node;
        } else {
            // 新Node属性
            node.prev = last;
            node.item = e;
            node.next = null;

            // 将上一个元素赋值，链接节点
            last.next = node;
        }

        // 此时的Node成为last
        last = node;
        size++;

        return true;
    }

    /**
     * 插入
     */
    @Override
    public void add(int index, E e) {
        logger.debug("index = {} , size = {}", index, size);

        indexCheck(index);

        //　如果index　==　size, 最后添加
        if (index == size) {
            linkLast(e);
        } else {
            linkBefore(e, node(index));
        }
    }

    /**
     * 末尾添加
     */
    private void linkLast(E e) {
        // 临时node，存放last
        final Node<E> temp = last;
        // 创建一个node, 结构：  last - e - null
        final Node<E> newNode = new Node<E>(temp, e, null);
        // 此时创建的node是last
        last = newNode;

        // 链接
        if (temp == null) {
            first = newNode;
        } else {
            temp.next = newNode;
        }

        size++;
    }

    /**
     * 指定index前加入
     */
    private void linkBefore(E e, Node<E> succ) {
        Node<E> temp = succ.prev;
        Node<E> newNode = new Node<E>(temp, e, succ);
        succ.prev = newNode;
        // 链接
        if (temp == null) {
            first = newNode;
        } else {
            temp.next = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        // 越界判断...
        indexCheck(index);

        return (E) node(index).item;

    }

    /**
     * 获取指定index的Node值
     */
    private Node node(int index) {
        // 二分法:index与中间值进行比较
        if (index < size << 1) {
            // 从first开始向后查找
            Node temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        } else {
            // 从last开始向前查找
            Node temp = last;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
            return temp;
        }
    }

    @Override
    public Object set(int index, Object o) {
        Node temp = node(index);

        Object x = temp.item;
        temp.item = o;

        return x;

    }

    @Override
    public E remove(int index) {
        indexCheck(index);

        // 当前需要删除Node
        Node temp = node(index);
        // 当前Node的前一个
        Node prevNode = temp.prev;
        // 当前Node的后一个
        Node nextNode = temp.next;

        // Node为first时
        if (prevNode == null) {
            first = nextNode;
        } else {
            prevNode.next = nextNode;
        }

        // Node为last时
        if (nextNode == null) {
            last = prevNode;
        } else {
            nextNode.prev = prevNode;
        }
        size--;

        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void indexCheck(int index) {
        // 判断索引值
        if (index < 0 || index >= size) {
            try {
                throw new IndexOutOfBoundsException();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    /**
     * 节点
     */
    private static class Node<E> {
        /**
         * 节点元素
         */
        E item;
        /**
         * 前一个节点
         */
        Node<E> prev;

        /**
         * 下一个节点
         */
        Node<E> next;

        Node() {
        }

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }

    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add("demo");
        list.add("b");
        list.add("c");
        list.add(1, "e");
        list.set(0, "f");
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("============");
        list.remove(3);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
