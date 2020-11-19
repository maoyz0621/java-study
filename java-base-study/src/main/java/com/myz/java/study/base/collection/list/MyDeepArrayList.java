package com.myz.java.study.base.collection.list;

import java.util.Iterator;

/**
 * 定义容器ArrayList
 * 实现Iterable接口，迭代输出
 * foreach
 * 使用泛型<E>
 *
 * @author maoyz on 18-2-14.
 */
public class MyDeepArrayList<E> implements Iterable<E> {

    /**
     * transient 修饰, 表示不能被序列化
     */
    transient Object[] elementData = {};

    private int size;

    /**
     * 默认初始化长度 10
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 共享的空的数组实例
     * 当使用 ArrayList() 或者 ArrayList(Collection<? extends E> c)
     * * 并且 c.size() = 0 的时候讲 elementData 数组讲指向这个实例对象。
     */
    private static final Object[] DEFAULT_EMPTY_ELEMENTDATE = {};

    /**
     * 1. 当使用 ArrayList(0)的时候使用
     * <p>
     * 再第一次 add 元素的时候将使用它来判断数组大小是否设置为 DEFAULT_CAPACITY
     */
    private static final Object[] EMPTY_ELEMENTDATE = {};

    /**
     * 空构造函数
     */
    public MyDeepArrayList() {
        this.elementData = DEFAULT_EMPTY_ELEMENTDATE;
    }

    /**
     * 传入初始大小
     *
     * @param initialCapacity 初始容量
     */
    public MyDeepArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elementData = EMPTY_ELEMENTDATE;
        } else {
            try {
                throw new IllegalArgumentException("initialCapacity必须为非负数");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 插入值
     *
     * @param o 　插入对象
     */
    public void add(E o) {
        if (elementData == DEFAULT_EMPTY_ELEMENTDATE) {

        }

        if (size >= elementData.length) {
            extendCapacity(size);
        }
        /**
         *  elementData[size++] = o;
         */
        elementData[size] = o;
        // 数组大小+1
        size++;
    }


    /**
     * 扩容
     */
    private void extendCapacity(int initialCapacity) {
        // 扩容1.5倍
        Object[] newValue = new Object[initialCapacity + initialCapacity >> 1];
        System.arraycopy(elementData, 0, newValue, 0, elementData.length);
        elementData = newValue;
    }


    public void add(int index, E e) {
        indexCheck(index);

        if (size >= elementData.length) {
            extendCapacity(size);
        }
        // 数组拷贝
        System.arraycopy(elementData, index, elementData, index + 1, size - index);

        elementData[index] = e;
        size++;
    }

    /**
     * 根据索引取值
     */
    public Object get(int index) {
        indexCheck(index);

        return elementData[index];
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

    /**
     * 移除指定对象
     */
    public void remove(E o) {
        // 元素为null时
        if (o != null) {
            for (int index = 0; index < elementData.length; index++) {
                // 底层调用equals()
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                }
            }
        } else {
            for (int index = 0; index < elementData.length; index++) {
                if (elementData[index] == null) {
                    fastRemove(index);
                }
            }
        }
    }

    private void fastRemove(int index) {
        indexCheck(index);

        // 删除位置后续数组的长度
        int removeNum = size - index - 1;
        if (removeNum > 0) {
            // 从index+1位置开始copy，至最后一位
            System.arraycopy(elementData, index + 1, elementData, index,
                    removeNum);
        }
        // 将最后一位设置为null，并size-1
        elementData[--size] = null;
    }

    /**
     * 指定位置赋值
     *
     * @param index 索引位置
     * @param o     　赋值
     */
    public Object set(int index, E o) {
        indexCheck(index);

        Object oldValue = elementData[index];
        elementData[index] = o;
        return oldValue;
    }

    /**
     * 清空
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            // 所有元素为null
            elementData[i] = null;
        }
        // 容量重新设置为0
        this.size = 0;
    }

    /**
     * 容器大小
     *
     * @return 容器值
     */
    public int size() {
        return size;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断是否包含指定元素
     *
     * @param o 　指定元素
     * @return true/false
     */
    public boolean contains(E o) {
        return indexof(o) >= 0;
    }

    /**
     * 获取指定元素的索引值
     *
     * @param o 指定元素
     * @return 索引值
     */
    private int indexof(E o) {
        // null
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        MyDeepArrayList<Integer> list = new MyDeepArrayList<Integer>();

        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.set(2, 22);
        list.add(3, 33);
        System.out.println(list.get(2));
        System.out.println(list.get(3));

        System.out.println("=========");

        for (Object o : list) {
            System.out.println(o);
        }

        System.out.println("=========");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            /**
             * 指针
             */
            private int cursor = -1;

            @Override
            public boolean hasNext() {
                return cursor + 1 < size;
            }

            @Override
            public Object next() {
                ++cursor;
                // cursor先移动1位
                return elementData[cursor];
            }
        };
    }
}
