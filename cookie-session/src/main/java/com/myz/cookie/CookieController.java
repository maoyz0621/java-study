/**
 * Copyright 2019 Inc.
 **/
package com.myz.cookie;

import com.myz.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author maoyz0621 on 19-4-21
 * @version: v1.0
 */
@RestController
public class CookieController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // @GetMapping("/cookie")
    @PostMapping("/cookie")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        request.getParameterMap();
        // 获得发出请求字符串的客户端地址
        String requestURI = request.getRequestURI();
        String requestURL = request.getRequestURL().toString();
        // 获得客户端向服务器端传送数据的方法有GET、POST、PUT等类型
        String method = request.getMethod();
        String remoteUser = request.getRemoteUser();
        // 获得客户端的IP地址
        // String remoteAddr = request.getRemoteAddr();
        String ip = IpUtils.getIpAddress(request);
        String remoteHost = request.getRemoteHost();
        // 返回发出请求的客户机的端口号
        int remotePort = request.getRemotePort();
        String localAddr = request.getLocalAddr();
        String localName = request.getLocalName();
        // 浏览器
        String userAgent = request.getHeader("User-Agent");

        String property = System.getProperty("os.name");
        String property1 = System.getProperty("os.version");
        String property2 = System.getProperty("os.arch");
        // 该方法返回请求中的参数部分（参数名+值）
        String queryString = request.getQueryString();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String domain = cookie.getDomain();
                int maxAge = cookie.getMaxAge();
                String path = cookie.getPath();
                int version = cookie.getVersion();
                String value = cookie.getValue();
                String comment = cookie.getComment();
                logger.info("{}, {}, {}, {}, {}, {}, {}", name, domain, maxAge, path, version, value, comment);
            }
        } else {
            // 设置cookie
            Cookie cookie = new Cookie("mao", "123");
            // 域
            cookie.setDomain(request.getRequestURI());
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }

        HttpSession session = request.getSession();
        String id = session.getId();
        long creationTime = session.getCreationTime();
        String sessionid = (String) session.getAttribute("SESSIONID");
        if (sessionid != null) {
            session.setAttribute("SESSIONID", sessionid);
            session.setMaxInactiveInterval(60 * 30);
        } else {
            session.setAttribute("SESSIONID", "aaaaaaaaaaa");
        }
        return "Ok";
    }
}
