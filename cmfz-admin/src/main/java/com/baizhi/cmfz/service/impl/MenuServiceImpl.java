package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.MenuDao;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/5 14:10.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao md;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Menu> queryMenuParentAndChild(){
        List<Menu> menus = null;
        menus = md.findMenuParentAndChild();
        return menus;
    }

    public MenuDao getMd() {
        return md;
    }

    public void setMd(MenuDao md) {
        this.md = md;
    }
}
