package com.cy.store.config;

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器拦截器的注册
 * 防止用户没有登录的情况下，访问了没有权限的页面
 * 将没有权限的页面最后都指向 login.html
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建自定义的拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();

        /**
         * 配置白名单：存放在一个List集合中
         * 白名单
         */
        List<String> patterns = new ArrayList<String>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");// 注册
        patterns.add("/web/login.html"); // 登录
        patterns.add("/web/index.html"); // 主页
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");

        // 完成拦截器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
