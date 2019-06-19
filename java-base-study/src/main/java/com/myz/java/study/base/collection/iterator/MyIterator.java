package com.myz.java.study.base.collection.iterator;

/**
 * 自定义迭代器
 *
 * @author maoyz on 18-2-22.
 */
public class MyIterator {

    private Object[] elem;

    private int size;

    /**
     * 指针
     */
    private int cursor = -1;

    public MyIterator(Object[] objects) {
        this.elem = objects;
        this.size = this.elem.length;
    }

    /**
     * 指针的值和size进行比较
     */
    public boolean hasNext() {
        return cursor + 1 < size;
    }

    /**
     * 游标先移动到下一位置,此时游标位置的元素
     *
     * @return 游标当前位置的元素
     */
    public Object next() {
        ++cursor;
        // cursor先移动1位
        return elem[cursor];
    }

    /**
     * 删除游标左边位置的元素，在执行完next之后，只能执行一次
     */
    public void remove() {
        // 移动数组元素
        System.arraycopy(elem, cursor + 1,
                elem, cursor, this.size - (cursor + 1));
        // 容量减少
        this.size--;
        // 指针回退
        cursor--;
    }

    public static void main(String[] args) {
        MyIterator myIterator = new MyIterator(new Object[]{"demo", "b", "c"});

        if (myIterator.hasNext()) {
            System.out.println(myIterator.next());
            myIterator.remove();
        }

        if (myIterator.hasNext()) {
            System.out.println(myIterator.next());
            myIterator.remove();
        }

        if (myIterator.hasNext()) {
            System.out.println(myIterator.next());
            myIterator.remove();
        }

    }
}
