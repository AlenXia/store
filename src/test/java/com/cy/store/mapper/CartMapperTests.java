package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

//标注当前的类是个测试类，不会随项目一块打包
@SpringBootTest
//表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class CartMapperTests {
   @Autowired
    private CartMapper cartMapper;

   @Test
    public void insert(){
       Cart cart=new Cart();
       cart.setUid(12);
       cart.setPid(10000011);
       cart.setNum(2);
       cart.setPrice(1000L);
       cartMapper.insert(cart);
   }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 4, "张三", new Date());
    }

   @Test
    public void findByUidAndPid(){
       System.err.println(cartMapper.findByUidAndPid(12, 10000011));
   }

    @Test
    public void findVOByUid() {
        System.err.println(cartMapper.findVOByUid(12));
    }

    @Test
    public void findByCid(){
        System.err.println(cartMapper.findByCid(2));
    }

    @Test
    public void findVOByCid(){
        Integer[] cids = {1, 2, 3,};
        System.out.println(cartMapper.findVOByCids(cids));
    }
}
