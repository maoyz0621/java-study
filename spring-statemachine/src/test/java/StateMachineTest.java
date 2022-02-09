/**
 * Copyright 2022 Inc.
 **/

import com.myz.statemachine.StateMachineApplication;
import com.myz.statemachine.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author maoyz0621 on 2022/2/8
 * @version v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StateMachineApplication.class)
public class StateMachineTest {

    @Autowired
    private IOrderService orderService;

    @Test
    public void test1(){
        orderService.pay(1L);
        orderService.deliver(1L);
        orderService.receive(1L);
    }

}