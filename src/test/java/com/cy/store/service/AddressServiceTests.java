package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//表示标注当前的类是个测试类，不会随项目一块打包
@SpringBootTest
//表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address=new Address();
        address.setPhone("15092115555");
        address.setName("南22鹏");
        addressService.addNewAddress(12,"管理员",address);
    }

    @Test
    public void setDefault(){
        addressService.setDefault(13,12,"管理员");
    }

    @Test
    public void delete(){
        addressService.delete(12,15,"管理員");
    }
}
