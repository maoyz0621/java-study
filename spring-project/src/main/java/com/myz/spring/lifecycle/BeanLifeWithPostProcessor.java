/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


/**
 * BeanFactoryPostProcessor
 * postProcessBeanFactory()
 * 可以在spring的bean创建之前，修改bean的定义属性。Spring允许BeanFactoryPostProcessor在容器实例化任何其它bean之前读取配置元数据，并可以根据需要进行修改，
 * 例如可以把bean的scope从singleton改为prototype，也可以把property的值给修改掉。可以同时配置多个BeanFactoryPostProcessor，并通过设置'order'属性来控制各个BeanFactoryPostProcessor的执行次序
 * <p>
 * BeanPostProcessor
 * 可以在spring容器实例化bean之后，在执行bean的初始化方法前后，添加一些自己的处理逻辑
 * 1）bean实现了InitializingBean接口，对应的方法为afterPropertiesSet
 * 2）在bean定义的时候，通过init-method设置的方法
 * <p>
 * 执行顺序: BeanFactoryPostProcessor -> BeanPostProcessor
 * <p>
 * https://blog.csdn.net/caihaijiang/article/details/35552859
 *
 * @author maoyz0621 on 19-7-3
 * @version: v1.0
 */
@Component
public class BeanLifeWithPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanLifeWithPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.info("********************** postProcessBeanFactory ************************", beanFactory.getBean(getClass()));
        System.out.println("BeanFactoryPostProcessor  postProcessBeanFactory()");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("helloworld4");
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();

        if (propertyValues.contains("desc")) {
            PropertyValue value = propertyValues.getPropertyValue("desc");
            System.out.println(value.getValue());

            // 改变属性值
            propertyValues.addPropertyValue("desc", "被我修改了, 哈哈哈");

        }

    }

}
