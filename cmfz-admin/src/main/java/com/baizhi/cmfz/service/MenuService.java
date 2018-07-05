package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/5 14:10.
 */
public interface MenuService {
    public List<Menu> queryMenuParentAndChild();
}
