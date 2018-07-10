package com.test.proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/9 21:59.
 */
public class test {
    public static void main(String[] args) {
        ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("classpath:aspectj.xml");
        AspectService as = (AspectService) ApplicationContext.getBean("aspectServicImpl");
        as.test();
    }
}
