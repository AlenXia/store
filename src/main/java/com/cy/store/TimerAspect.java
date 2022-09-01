package com.cy.store;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // 将当前类的对象创建使用维护交由Spring容器维护
@Aspect
public class TimerAspect {
    @Around("execution(* com.cy.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        // 记录当前时间
        long start=System.currentTimeMillis();
        Object result = pjp.proceed();
        // 后记录当前时间
        long end= System.currentTimeMillis();
        System.out.println("耗时："+(end - start));
        return result;
    }
}
