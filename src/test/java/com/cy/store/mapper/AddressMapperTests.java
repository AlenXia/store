package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
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
public class AddressMapperTests {
   @Autowired
    private AddressMapper addressMapper;

   @Test
    public void insert(){
       Address address=new Address();
       address.setUid(12);
       address.setPhone("123456");
       address.setName("呵呵");
       addressMapper.insert(address);
   }

   @Test
   public void countByUid(){
       Integer count = addressMapper.countByUid(12);
       System.out.println(count);
   }

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(12);
        System.err.println(list);
    }

    @Test
    public void findByAid(){
        List<Address> list = addressMapper.findByUid(12);
        System.err.println(list);
    }

    @Test
    public void updateNonDefault(){
        addressMapper.updateNonDefault(12);
    }

    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(10, "名", new Date());
    }

    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(1);
    }

    @Test
    public void findLastModified(){
        System.err.println(addressMapper.findLastModified(12));
    }
}
