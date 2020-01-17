package com.myz.java.study.base.collection.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 定义容器ArrayList，相当于动态数组
 * 实现Iterable接口，迭代输出
 * foreach
 *
 * @author maoyz on 18-2-14.
 */
public class MyArrayList<E> implements Iterable<E>, List<E> {
    private static final Logger logger = LoggerFactory.getLogger(MyArrayList.class);
    /**
     * 被用于默认大小的空实例的共享数组实例。ThreadUseObject
     * 其与EMPTY_ELEMENTDATA的区别是：当我们向数组中添加第一个元素时，知道数组该扩充多少
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 被用于空实例的共享空数组实例
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     * 默认初始容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Object[]类型的数组，保存了添加到ArrayList中的元素。ArrayList的容量是该Object[]类型数组的长度
     * 当第一个元素被添加时，任何空ArrayList中的elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA将会被扩充到DEFAULT_CAPACITY（默认容量）
     */
    transient Object[] elementData;

    /**
     * 动态数组的实际大小
     */
    private int size;

    /**
     * 构造一个默认初始容量为10的空列表
     */
    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 传入初始大小
     *
     * @param initialCapacity 初始容量
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            // 新建一个数组
            elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("initialCapacity必须为非负数");
        }
    }

    /**
     * 插入值
     *
     * @param o 　插入对象
     */
    @Override
    public boolean add(Object o) {
        ensureCapacityInternal(size + 1);

        // 数组大小+1
        elementData[size++] = o;
        logger.debug("size = {}", size);
        return true;
    }

    /**
     * 确保内部容量，实现自身容量的增加
     */
    private void ensureCapacityInternal(int minCapacity) {
        // 如果此时elementData为空，使用默认容量10
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        logger.debug("minCapacity = {}", minCapacity);

        extendCapacity(minCapacity);
    }

    /**
     * 扩容
     *
     * @param minCapacity 最小容量
     */
    private void extendCapacity(int minCapacity) {
        // 数组大小
        int oldCapacity = elementData.length;
        // 判断容量和数组长度的大小
        if (minCapacity - oldCapacity > 0) {
            // 容量扩容大小1.5倍
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            // 扩容容量比容量小，使用初始容量
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }

            Object[] newValue = new Object[newCapacity];
            /* 数组拷贝
              src - the source array. 原数组
              srcPos - starting position in the source array. 从元数据的起始位置开始
              dest - the destination array. 目标数组
              destPos - starting position in the destination data. 目标数组的开始起始位置
              length - the number of array elements to be copied. 要copy的数组的长度 */
            System.arraycopy(elementData, 0, newValue, 0, oldCapacity);
            elementData = newValue;
        }
    }

    /**
     * 指定索引位置添加
     */
    @Override
    public void add(int index, Object o) {
        indexCheck(index);

        extendCapacity(size + 1);

        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);

        elementData[index] = o;
        size++;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * 根据索引取值
     */
    @Override
    public E get(int index) {
        indexCheck(index);

        return (E) elementData[index];
    }

    /**
     * 判断索引值
     */
    private void indexCheck(int index) {
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
     *
     * @param o 指定对象
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < elementData.length; index++) {
                if (elementData[index] == null) {
                    // 删除null
                    fastRemove(index);
                }
            }
        } else {
            for (int index = 0; index < elementData.length; index++) {
                // 底层调用equals()
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
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
     * @param index   索引位置
     * @param element 赋值
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        indexCheck(index);

        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 清空
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            // 所有元素为null，以便CG回收
            elementData[i] = null;
        }
        // 数组大小重新设置为0
        this.size = 0;
    }

    /**
     * 容器大小
     *
     * @return 数组实际大小
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 判断是否为空
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断是否包含指定元素
     *
     * @param o 　指定元素
     */
    @Override
    public boolean contains(Object o) {
        // -1表示不存在
        return indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        // 1 null
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            // 2 非null
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        // 不存在，返回-1
        return -1;
    }

    /**
     * 获取指定元素的索引值, 反向查找
     */
    @Override
    public int lastIndexOf(Object o) {
        // 1 null
        if (o == null) {
            for (int i = size - 1; i > 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            // 2 非null
            for (int i = size - 1; i > 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        // 不存在，返回-1
        return -1;
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

    /**
     * 返回ArrayList的Object数组
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * 返回ArrayList的模板数组。所谓模板数组，即可以将T设为任意的数据类型
     */
    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList(5);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.set(2, "aaa");
        list.add(3, "b");
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

        System.out.println("---------------------");
        // list.add("demo");
        // list.remove(1);
        //
        // System.out.println(list.get(1));
        System.out.println(list.size());
        // list.clear();
        // System.out.println(list.isEmpty());
        // System.out.println(list.contains(1));

    }
}
