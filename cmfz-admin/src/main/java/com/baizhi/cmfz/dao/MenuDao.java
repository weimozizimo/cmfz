package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Menu;

import java.util.List;

/**
 * @Description 对菜单进行数据库操作的接口类
 * @Author weizimo
 * @Time 2018/7/5 11:38.
 */
public interface MenuDao {

    public List<Menu> findMenuParentAndChild();
}
