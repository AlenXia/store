package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层类的基类
 */
public class BaseController {
    // 表示操作成功的状态
    public static final int OK = 200;

    // 请求处理方法，这个方法的返回值就是需要传递数据
    // 自动将异常对象传递给此方法的参数列表上
    // 当前项目中产生了异常
    @ExceptionHandler({ServiceException.class, FileUploadException.class})//用于统一处理抛出的异常
    public JsonResult<Void> handlerException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户数据不存在的异常");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("用户名的密码错误的异常");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("用户的收货地址超出上限的异常");
        } else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMessage("用户收货地址不存在的异常");
        } else if (e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMessage("收货地址数据非法访问的异常");
        } else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("商品数据不存在的异常" );
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("购物车数据不存在" );
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        } else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("更新数据时产生未知的异常");
        } else if (e instanceof DeleteException) {
            result.setState(5002);
            result.setMessage("刪除数据时产生未知的异常");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("文件为空");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件过大");
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("文件类型错误");
        } else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("文件状态异常");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("文件上传异常");
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     *
     * @param session session对象
     * @return 当前登录的用户uid值
     */
    protected final Integer getUidFromSession(
            HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的userna
     *
     * @param session session对象
     * @return 当前登录用户的用户名
     * 在实现类中重写父类中的toString(),不是句柄信息输出
     */
    protected final String getUsernameFromSession(
            HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
