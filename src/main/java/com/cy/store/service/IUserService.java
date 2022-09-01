package com.cy.store.service;

import com.cy.store.entity.User;

public interface IUserService {
    //注册
    void reg(User user);

    /**
     * 用户登录功能
     * @param username
     * @param password
     * @return 当前匹配的用户数据，没有则返回NULL
     */
    User login(String username, String password);

    /**
     * 修改密码功能
     * @param uid 修改密码的用户id
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * 根据用户的id查询用户的数据
     * @param uid 用户的id
     * @return 用户的数据
     */
    User getByUid(Integer uid);

    /**
     * 更新用户的数据操作
     * @param uid 用户的id
     * @param username 用户的名称
     * @param user 用户对象的数据
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * 修改用户的头像
     * @param uid 用户的id
     * @param username 用户的名称
     * @param avatar 路径
     */
    void changeAvatar(Integer uid,
                      String username,
                      String avatar);
}
