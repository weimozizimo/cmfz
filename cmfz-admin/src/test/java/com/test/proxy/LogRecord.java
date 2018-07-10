package com.test.proxy;

import com.baizhi.cmfz.dao.LogDao;
import com.baizhi.cmfz.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/9 20:24.
 */
@Aspect
@Component
public class LogRecord {
    /**
     * 声明切入点表达式
     */
    @Pointcut("execution(* com.test.proxy.*.*(..))")
    public void pc(){}

    @Before("pc()")
    public void before(){
        System.out.println("------前置通知-------");
    }



}
