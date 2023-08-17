/**
 * Copyright 2023 Inc.
 **/
package com.myz.spring.rpc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

/**
 * @author maoyz0621 on 2023/7/25
 * @version v1.0
 */
@Component
public class RpcClientRegistry implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.addIncludeFilter(new AnnotationTypeFilter(RpcClient.class));

        // 可以使用EnableRpcXxx 配置的扫包路径
        String basePackage = "com.myz.spring.rpc";
        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackage);

        for (BeanDefinition candidateComponent : candidateComponents) {
            if (candidateComponent instanceof AnnotatedBeanDefinition) {

                AnnotationMetadata metadata = ((ScannedGenericBeanDefinition) candidateComponent).getMetadata();
                GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition(candidateComponent);
                genericBeanDefinition.setBeanClass(RpcClientFactoryBean.class);
                genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(metadata.getClassName());

                Map<String, Object> attributes = metadata.getAnnotationAttributes(RpcClient.class.getName(), true);

                for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                    genericBeanDefinition.getPropertyValues().addPropertyValue(entry.getKey(), entry.getValue());
                }

                genericBeanDefinition.getPropertyValues().add("feignInterface", metadata.getClassName());
                genericBeanDefinition.setAutowireMode(AUTOWIRE_BY_TYPE);
                //
                registry.registerBeanDefinition(metadata.getClassName(), genericBeanDefinition);
            }
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private ClassPathScanningCandidateComponentProvider getScanner() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isIndependent() && !beanDefinition.getMetadata().isAnnotation();
            }
        };

        return scanner;
    }
}