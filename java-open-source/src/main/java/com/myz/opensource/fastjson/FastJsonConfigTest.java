package com.myz.opensource.fastjson;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/7 20:07
 */
public class FastJsonConfigTest {

    @Test
    public void test1() {
        FastJsonBean bean = new FastJsonBean();
        bean.setName("a");
        String s = JSON.toJSONString(bean);
        System.out.println(s);
    }

    @Test
    public void test2() {
        FastJsonBean bean = JSON.parseObject("{\"name\":\"a*\"}", FastJsonBean.class);
        System.out.println(bean.getName());
    }
}
