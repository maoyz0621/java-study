package com.myz.java.study.base.clone.base;

/**
 * https://www.cnblogs.com/Qian123/p/5710533.html#4289901
 *
 * @author maoyz
 */
public class StudentClone implements Cloneable {

    private int number;

    private Integer age;

    private String addr;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Object clone() {
        StudentClone studentShallowClone = null;
        try {
            studentShallowClone = (StudentClone) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return studentShallowClone;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StudentClone{");
        sb.append("number=").append(number);
        sb.append(", age=").append(age);
        sb.append(", addr='").append(addr).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
