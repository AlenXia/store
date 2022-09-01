package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
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
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void  reg() {
        try {
            User user = new User();
            user.setUsername("yuan1111");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            //获取类的对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的信息
            System.out.println(e.getMessage());
        }
    }

//    @Test
//    public void findByUsername(){
//        User user = userMapper.findByUsername("tim");
//        System.out.println(user);
//    }

    @Test
    public void login(){
        User user = userService.login("yuan111", "123");
        System.out.println(user);
    }

    @Test
    public void changePassword(){
        userService.changePassword(12,"yuan1111","123","321");
    }

    @Test
    public void getByUid(){
        System.err.println(userService.getByUid(12));
    }

    @Test
    public void changeInfo(){
        User user=new User();
        user.setPhone("5634");
        user.setEmail("klhsdgf@qq.com");
        user.setGender(0);
        userService.changeInfo(12,"管理员",user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(
                12,
                "/upload/test.png",
                "小马哥");
    }
}
