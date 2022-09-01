package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//表示标注当前的类是个测试类，不会随项目一块打包
@SpringBootTest
//表示启动这个单元测试类
@RunWith(SpringRunner.class)
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DistrictServiceTests {
    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent() {
        // 86表示中国
        List<District> list = districtService.getByParent("86");
        for (District d : list) {
            System.err.println(d);
        }
    }

}
