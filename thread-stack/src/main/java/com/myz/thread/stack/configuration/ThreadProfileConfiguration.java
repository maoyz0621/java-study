package com.myz.thread.stack.configuration;

import com.myz.thread.stack.intercepter.ThreadProfileInterceptor;
import com.myz.thread.stack.intercepter.MethodProfileInterceptor;
import com.myz.thread.stack.profile.ThreadProfile;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 根据注释 proxyBeanMethods 是为了让使用 @Bean 注解的方法被代理而实现 bean 的生命周期的行为。
 * <p>
 * 设置为 true，那么直接调用方法获取 bean，不会创建新的 bean，而是会走 bean 的生命周期的行为。
 * 设置为 false, 那么直接调用方法获取 bean，会创建新的 bean，且不会走 bean 的生命周期的行为。
 *
 * @author maoyz
 * @version V1.0
 * @date 2021/9/27 14:34
 */
@Configuration(proxyBeanMethods = true)
public class ThreadProfileConfiguration implements WebMvcConfigurer, InitializingBean {

    @Value("${project.thread-profile-threshold:500}")
    private int threshold;

    @Value("${project.thread-profile-method-interceptor-expression:execution(* com.myz.*.*(..))}")
    private String expression;

    @Override
    public void afterPropertiesSet() throws Exception {
        ThreadProfile.init(threshold);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ThreadProfileInterceptor()).order(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * 编程式AOP切面
     *
     * @return
     */
    @Bean
    public AspectJExpressionPointcutAdvisor aspectAdvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        advisor.setAdvice(new MethodProfileInterceptor());
        return advisor;
    }

    @Autowired
    private WebApplicationContext context;

    @PostConstruct
    public void methodInit() {
        // 获取
        RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        Map<String, String> urlHandler = new HashMap<>();
        map.forEach((k, v) -> {
            Set<String> patterns = k.getPatternsCondition().getPatterns();
            for (String pattern : patterns) {
                urlHandler.put(pattern, v.getMethod().getDeclaringClass().getName() + "." + v.getMethod().getName());
            }
        });
        // // 初始化map集合
        // List<Map<String, String>> listMap = new ArrayList<>();
        // for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
        //     Map<String, String> initMap = new HashMap<>();
        //     RequestMappingInfo info = entry.getKey();
        //     HandlerMethod handlerMethod = entry.getValue();
        //     String className = handlerMethod.getMethod().getDeclaringClass().getName();
        //     String name = handlerMethod.getMethod().getName();
        //     urlHandler.put(info.getName(), className + name);
        //
        //     // 请求类型
        //     RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
        //     // 请求url
        //     PatternsRequestCondition pattern = info.getPatternsCondition();
        //     // 如果类型不为空则获取
        //     Set<RequestMethod> methods = methodsCondition.getMethods();
        // }
    }
}
