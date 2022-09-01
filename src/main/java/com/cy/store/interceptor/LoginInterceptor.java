package com.cy.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 定义一个拦截器*/
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有重新定向到登录界面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器
     * @return 如果true放行，如果false拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // HttpServletRequest对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null) {
            // 说明用户没有登录过系统，则重定向到login.html
            response.sendRedirect("/web/login.html");
            // 结束后续的调用
            return false;
        }
        // 请求放行
        return true;
    }
}
