package com.myz.spring.test;

import com.myz.spring.listener.EventPublishService;
import com.myz.spring.listener.ListenerConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author maoyz0621
 */
public class TestListenerSpring {



    @Test
    public void testListener() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ListenerConfig.class);

        // 这是自定义 publishEvent
        context.publishEvent(new ApplicationEvent("自定义ApplicationEvent") {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });

        EventPublishService bean = context.getBean(EventPublishService.class);
        bean.push();
        context.close();

    }

}
