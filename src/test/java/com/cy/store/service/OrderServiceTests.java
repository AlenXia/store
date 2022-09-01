package com.cy.store.service;

import com.cy.store.entity.District;
import com.cy.store.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//表示标注当前的类是个测试类，不会随项目一块打包
@SpringBootTest
//表示启动这个单元测试类
@RunWith(SpringRunner.class)
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;

    @Test
    public void creat() {
        Integer[] cids = {6,7};
        Order order = orderService.create(19, 15, "红红", cids);
        System.out.println(order);
    }

}
