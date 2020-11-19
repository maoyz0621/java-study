package com.myz.cache;

import com.myz.cache.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionCacheApplicationTests {

    @Autowired
    MenuService menuService;

    @Test
    public void testCache() {
        System.out.println("call:" + menuService.getMenuList());
        System.out.println("call:" + menuService.getMenuList());
    }

    @Test
    public void testInnerCall() {
        System.out.println("call:" + menuService.getRecommends());
        System.out.println("call:" + menuService.getRecommends());
    }

    @Test
    public void testInnerCallContext() {
        System.out.println("call:" + menuService.getRecommendsWithContext());
        System.out.println("call:" + menuService.getRecommendsWithContext());
    }

    @Test
    public void testInnerCallAopContext() {
        System.out.println("call:" + menuService.getRecommendsWithAopContext());
        System.out.println("call:" + menuService.getRecommendsWithAopContext());
    }

}
