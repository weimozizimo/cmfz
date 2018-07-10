package com.baizhi.cmfz.proxy;

import com.baizhi.cmfz.dao.LogDao;
import com.baizhi.cmfz.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
    @Autowired
    private LogDao ld;
    /**
     * 声明切入点表达式
     */
    @Pointcut("execution(* com.baizhi.cmfz.service.impl.*.*modify(..))")
    public void operLog(){}

    @Around("operLog()")
    public Object record(ProceedingJoinPoint pjp) throws Throwable { //连接点
        String result = null;
        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        System.out.println("--------------------开始记录---------------------");
        //调用传递
        //obj 原始方法的返回值
        Object obj = pjp.proceed();
        Log log = new Log();
        log.setId(UUID.randomUUID().toString().replace("-",""));
        log.setUser((String) session.getAttribute("user"));
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        Method method =  methodSignature.getMethod();
        log.setAction(method.getName());
        log.setTime(new Date());
        log.setResource(pjp.getArgs()[0].getClass().getName());
        String message = null;
        for (Object ob:pjp.getArgs() ){
            message+=ob.toString();
        }
        log.setMessage(message);
        System.out.println(message);
        System.out.println(method.getName());
        System.out.println((String)session.getAttribute("user"));
        System.out.println(pjp.getArgs()[0].getClass().getName());
        System.out.println(log.toString());
        ld.insertLog(log);
        return obj;
    }

}