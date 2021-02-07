package com.myz.java.study.base.net.url;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL
 * 统一资源定位符
 * http://study.163.com:80/course/courseLearn.htm?courseId=343001#/learn/video?lessonId=1051635894&courseId=343001
 *
 * @author maoyz on 18-3-18.
 */
public class URLTest {

    private URL url = null;

    @Test
    public void test() throws MalformedURLException {
        url = new URL("http:" +
                "//study.163.com:80" +
                "/course/courseLearn.htm?courseId=343001" +
                "#/learn/video?lessonId=1051635894&courseId=343001");
        System.out.println("协议名称:" + url.getProtocol());
        System.out.println("域名:" + url.getHost());
        System.out.println("端口号:" + url.getPort());
        System.out.println("文件名:" + url.getFile());
        System.out.println("锚点:" + url.getRef());
        System.out.println("查询部分:" + url.getQuery());
    }

}
