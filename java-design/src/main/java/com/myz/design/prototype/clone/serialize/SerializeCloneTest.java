package com.myz.design.prototype.clone.serialize;


import com.myz.design.prototype.clone.City;
import com.myz.design.prototype.clone.Province;
import org.junit.Test;

/**
 * @author maoyz
 */
public class SerializeCloneTest {

    /**
     * 对象 Cloneable
     */
    @Test
    public void testShallowClone() {
        StudentSerializableClone deepClone = new StudentSerializableClone();
        deepClone.setNumber(1);
        deepClone.setAge(10);
        deepClone.setAddr("anhui");

        Province province = new Province();
        province.setProvince("aaaa");
        City city = new City();
        city.setCity("a000");
        deepClone.setProvince(province);
        deepClone.setCity(city);
        StudentSerializableClone deepClone2 = (StudentSerializableClone) deepClone.clone();
        // false
        System.out.println(deepClone == deepClone2);
        // false
        System.out.println("city = " + (deepClone.getCity() == deepClone2.getCity()));
        // false
        System.out.println("province = " + (deepClone.getProvince() == deepClone2.getProvince()));
        System.out.println(deepClone);
        System.out.println(deepClone2);

        // deepClone 变化
        deepClone.setNumber(11);
        deepClone.setAge(101);
        deepClone.setAddr("anhui1");
        deepClone.getProvince().setProvince("aaaa1");
        deepClone.getCity().setCity("a0001");
        // StudentClone{number=11, age=101, addr='anhui1', province=Province{province='aaaa1'}, city=City{city='a0001'}}
        System.out.println("student1发生变化: " + deepClone);
        // StudentClone{number=1, age=10, addr='anhui', province=Province{province='aaaa1'}, city=City{city='a000'}}
        System.out.println("student1发生变化: " + deepClone2);

        // student变化
        deepClone2.setNumber(12);
        deepClone2.setAge(102);
        deepClone2.setAddr("anhui2");
        deepClone2.getProvince().setProvince("aaaa2");
        deepClone2.getCity().setCity("a0002");
        // {"addr":"anhui1","age":101,"city":{"city":"a0001"},"number":11,"province":{"province":"aaaa1"}}
        System.out.println("student2发生变化: " + deepClone);
        // {"addr":"anhui2","age":102,"city":{"city":"a0002"},"number":12,"province":{"province":"aaaa2"}}
        System.out.println("student2发生变化: " + deepClone2);
    }

}
