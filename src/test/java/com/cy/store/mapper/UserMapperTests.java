package com.cy.store.mapper;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

//标注当前的类是个测试类，不会随项目一块打包
@SpringBootTest
//表示启动这个单元测试类
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("ti88m");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("ti88m");
    }

    @Test
    public void updatePasswordByUid() {
        userMapper.updatePasswordByUid(
                9, "321",
                "管理員", new Date());
    }

    @Test
    public void findByUid() {
        System.out.println(userMapper.findByUid(9));
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(5);
        user.setPhone("12154646");
        user.setEmail("fgjkh@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid() {
        userMapper.updateAvatarByUid(
                12,
                "/upload/avatar.png",
                "管理员",
                new Date());
    }
}
