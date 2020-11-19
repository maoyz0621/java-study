package com.myz.design.prototype.clone.shallow;

import com.myz.design.prototype.clone.City;
import com.myz.design.prototype.clone.Province;
import org.junit.Test;

/**
 * @author maoyz
 */
public class ShallowCloneTest {

    /**
     * 对象 Cloneable
     */
    @Test
    public void testShallowClone() {
        StudentShallowClone studentShallowClone1 = new StudentShallowClone();
        studentShallowClone1.setNumber(1);
        studentShallowClone1.setAge(10);
        studentShallowClone1.setAddr("anhui");

        Province province = new Province();
        province.setProvince("aaaa");
        City city = new City();
        city.setCity("a000");
        studentShallowClone1.setProvince(province);
        studentShallowClone1.setCity(city);
        StudentShallowClone studentShallowClone2 = (StudentShallowClone) studentShallowClone1.clone();
        // false
        System.out.println(studentShallowClone1 == studentShallowClone2);
        System.out.println(studentShallowClone1);
        System.out.println(studentShallowClone2);

        // studentShallowClone1 变化
        studentShallowClone1.setNumber(11);
        studentShallowClone1.setAge(101);
        studentShallowClone1.setAddr("anhui1");
        studentShallowClone1.getProvince().setProvince("aaaa1");
        studentShallowClone1.getCity().setCity("a0001");
        // StudentClone{number=11, age=101, addr='anhui1', province=Province{province='aaaa1'}, city=City{city='a0001'}}
        System.out.println("student1发生变化: " + studentShallowClone1);
        // StudentClone{number=1, age=10, addr='anhui', province=Province{province='aaaa1'}, city=City{city='a0001'}}
        System.out.println("student1发生变化: " + studentShallowClone2);

        // student变化
        studentShallowClone2.setNumber(12);
        studentShallowClone2.setAge(102);
        studentShallowClone2.setAddr("anhui2");
        studentShallowClone2.getProvince().setProvince("aaaa2");
        studentShallowClone2.getCity().setCity("a0002");
        // StudentClone{number=11, age=101, addr='anhui1', province=Province{province='aaaa2'}, city=City{city='a0002'}}
        System.out.println("student2发生变化: " + studentShallowClone1);
        // StudentClone{number=12, age=102, addr='anhui2'}
        System.out.println("student2发生变化: " + studentShallowClone2);
    }

}
