package com.test.proxy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/9 21:57.
 */
@Service
public class AspectServicImpl implements AspectService{

    public void test() {
        System.out.println("hello");
    }
}
