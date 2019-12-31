package com.myz.design.prototype.clone.serialize;

import com.alibaba.fastjson.JSON;
import com.myz.design.prototype.clone.City;
import com.myz.design.prototype.clone.Province;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

/**
 * 推薦使用
 * https://www.cnblogs.com/Qian123/p/5710533.html#4289901
 *
 * @author maoyz
 */
public class StudentSerializableClone implements Cloneable, Serializable {

    private static final long serialVersionUID = -214334847969746326L;

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

    @Override
    public StudentSerializableClone clone() {
        StudentSerializableClone clone = null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            bis = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bis);
            clone = (StudentSerializableClone) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(baos);
            IOUtils.closeQuietly(oos);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(ois);
        }
        return clone;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
