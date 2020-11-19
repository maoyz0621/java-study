/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.opensource.requests;

import net.dongliu.commons.annotation.Nullable;
import net.dongliu.requests.*;
import net.dongliu.requests.json.TypeInfer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://github.com/hsiafan/requests
 *
 * @author maoyz0621 on 2019/3/12
 * @version: v1.0
 */
public class RequestsDemo {

    private static final Logger logger = LoggerFactory.getLogger(RequestsDemo.class);

    String url = "https://www.baidu.com/s?tn=02049043_8_pg&ch=3&isource=infinity&iname=baidu&itype=web&ie=utf-8&wd=raw";

    /**
     * Get请求
     */
    @Test
    public void testGet() {
        String url = "https://www.zhihu.com/signup?next=%2F";
        // get()请求,  send()发送,
        String resp = Requests.get(url).send().readToText();
        logger.debug("resp = {}", resp);

        Response<String> textResponse = Requests.get(url).send().toTextResponse();
        int statusCode = textResponse.statusCode();
        String body = textResponse.body();
        String url1 = textResponse.url();
        logger.debug("statusCode = {}, body = {},  url1 = {}", statusCode, body, url1);
    }

    /**
     * Post请求
     */
    public void testPost() {
        String url = "";
        Response<String> textResponse = Requests.post(url).send().toTextResponse();
    }

    @Test
    public void testResponse() {
        String url = "https://www.baidu.com/s?tn=02049043_8_pg&ch=3&isource=infinity&iname=baidu&itype=web&ie=utf-8&wd=raw";
        RawResponse response = Requests.get(url).send();
        int statusCode = response.statusCode();
        String header = response.getHeader("Content-Length");
        @Nullable Cookie cookie = response.getCookie("");
        String body = response.readToText();
        logger.debug("statusCode = {}, header = {}, cookie = {}, body = {}", statusCode, header, cookie, body);
    }

    @Test
    public void testResponse1() {
        String url = "https://www.baidu.com/s?tn=02049043_8_pg&ch=3&isource=infinity&iname=baidu&itype=web&ie=utf-8&wd=raw";
        // string
        String text = Requests.get(url).send().readToText();
        logger.debug("text = {}", text);
        // bytes
        byte[] bytes = Requests.get(url).send().readToBytes();
        logger.debug("bytes = {}", bytes);
        // file
        Requests.get(url).send().writeToFile("../a.text");
    }

    /**
     * 转码
     */
    @Test
    public void testCharset() {
        Requests.get(url).charset(StandardCharsets.UTF_8).send().readToText();
        Requests.get(url).send().charset(StandardCharsets.UTF_8).readToText();
    }

    /**
     * 传递参数
     */
    @Test
    public void testGetParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("a", 1);
        params.put("b", 2);
        Requests.get(url).params(params).send().readToText();

        Requests.get(url).params(Parameter.of("a", 1), Parameter.of("b", 2)).send().readToText();
    }

    /**
     * www-form-encoded parameters
     * <p>
     * 注：The forms parameter should only works with post method.
     */
    @Test
    public void testPostWwwFormEncodedParameters() {
        Map<String, Object> params = new HashMap<>();
        params.put("a", 1);
        params.put("b", 2);
        Requests.post(url).body(params).send().readToText();

        Requests.post(url).body(Parameter.of("a", 1), Parameter.of("b", 2)).send().readToText();
    }

    @Test
    public void testHeader() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("a", 1);
        headers.put("b", 2);
        Requests.get(url).headers(headers).send().readToText();

        Requests.get(url).headers(new Header("a", 1), new Header("b", 2)).send().readToText();
    }

    @Test
    public void testCookie() {
        Map<String, Object> cookies = new HashMap<>();
        cookies.put("a", 1);
        cookies.put("b", 2);
        Requests.get(url).cookies(cookies).send().readToText();

        Requests.get(url).cookies(Parameter.of("a", 1), Parameter.of("b", 2)).send().readToText();
    }

    /**
     * Json
     */
    @Test
    public void testJson() {
        // send json body, content-type is set to application/json
        RawResponse response = Requests.post("http://.../update_person")
                .jsonBody("")
                .send();

        // response body as json, to value
        Person person = Requests.post("http://.../get_person")
                .params(Parameter.of("uuid", 101))
                .send().readToJson(Person.class);

        // json body decoder to generic type
        List<Person> persons = Requests.post("http://.../get_person_list")
                .send().readToJson(new TypeInfer<List<Person>>() {

                });
    }

    /**
     * basicAuth
     */
    @Test
    public void testAuth() {
        Requests.get(url).basicAuth("user", "password").send().readToText();
    }

    /**
     * 重定向 followRedirect()
     */
    @Test
    public void testRedirection() {
        Requests.get(url).followRedirect(false).send().readToText();
    }

    /**
     * set connect timeout and socket timeout
     */
    @Test
    public void testTimeout() {
        Requests.get(url).socksTimeout(20000).connectTimeout(30_000).send().readToText();
    }

    /**
     * https请求
     */
    @Test
    public void testHttps() {
        Requests.get(url).verify(false).send().readToText();
    }

    /**
     * 代理
     */
    @Test
    public void testProxy() {
        // http proxy
        Requests.get(url).proxy(Proxies.httpProxy("127.0.0.1", 8081)).send();
        // socks proxy proxy
        Requests.get(url).proxy(Proxies.socksProxy("127.0.0.1", 1080)).send();
    }

    /**
     * Session maintains cookies, basic auth and maybe other http context for you, useful when need login or other situations. Session have the same usage as Requests.
     */
    @Test
    public void testSession() {
        Session session = Requests.session();
        session.get(url).send().readToText();
    }
}
