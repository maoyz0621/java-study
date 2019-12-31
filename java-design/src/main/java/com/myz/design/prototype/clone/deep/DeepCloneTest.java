package com.myz.design.prototype.clone.deep;

import com.myz.design.prototype.clone.City;
import com.myz.design.prototype.clone.Province;
import org.junit.Test;

/**
 * @author maoyz
 */
public class DeepCloneTest {

    /**
     * 对象 Cloneable
     */
    @Test
    public void testShallowClone() {
        StudentDeepClone deepClone = new StudentDeepClone();
        deepClone.setNumber(1);
        deepClone.setAge(10);
        deepClone.setAddr("anhui");

        Province province = new Province();
        province.setProvince("aaaa");
        City city = new City();
        city.setCity("a000");
        deepClone.setProvince(province);
        deepClone.setCity(city);
        StudentDeepClone deepClone2 = (StudentDeepClone) deepClone.clone();
        // false
        System.out.println(deepClone == deepClone2);
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
        // StudentClone{number=11, age=101, addr='anhui1', province=Province{province='aaaa2'}, city=City{city='a0001'}}
        System.out.println("student2发生变化: " + deepClone);
        // StudentClone{number=12, age=102, addr='anhui2', province=Province{province='aaaa2'}, city=City{city='a0002'}}
        System.out.println("student2发生变化: " + deepClone2);
    }

}
