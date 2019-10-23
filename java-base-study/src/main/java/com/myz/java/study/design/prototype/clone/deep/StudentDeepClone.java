package com.myz.java.study.design.prototype.clone.deep;

import com.myz.java.study.design.prototype.clone.City;
import com.myz.java.study.design.prototype.clone.Province;

/**
 * https://www.cnblogs.com/Qian123/p/5710533.html#4289901
 * @author maoyz
 */
public class StudentDeepClone implements Cloneable {

    private int number;

    private Integer age;

    private String addr;

    private Province province;

    private City city;

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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    /**
     * 没有clone() 引用类型
     * @return
     */
    @Override
    public Object clone() {
        StudentDeepClone studentDeepClone = null;
        try {
            studentDeepClone = (StudentDeepClone) super.clone();
            studentDeepClone.city = (City) city.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return studentDeepClone;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StudentClone{");
        sb.append("number=").append(number);
        sb.append(", age=").append(age);
        sb.append(", addr='").append(addr).append('\'');
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append('}');
        return sb.toString();
    }
}
