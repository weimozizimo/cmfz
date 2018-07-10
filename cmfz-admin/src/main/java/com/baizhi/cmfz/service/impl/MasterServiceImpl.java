package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.MasterDao;
import com.baizhi.cmfz.entity.Master;
import com.baizhi.cmfz.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/8 10:21.
 */
@Service
@Transactional
public class MasterServiceImpl implements MasterService {
    @Autowired
    private MasterDao masterDao;

    public  Map<String,Object> queryByName(Integer pageIndex, Integer pageLine,String masterName) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Master> masters = masterDao.selectByName((pageIndex-1)*pageLine,pageLine,masterName);
        Integer count = masterDao.getCountByName(masterName);
        map.put("total",count);
        map.put("rows",masters);
        return map;
    }

    public Map<String,Object> queryAll(Integer pageIndex, Integer pageLine) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Master> masters = masterDao.selectAll((pageIndex-1)*pageLine,pageLine);
        Integer count = masterDao.getCount();
        map.put("total",count);
        map.put("rows",masters);
        return map;
    }
    public List<Master> queryAll() {
        List<Master> masters = masterDao.selectAll();
        return masters;
    }

    public Map<String,Object> queryById(Integer masterId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Master> list = new ArrayList<Master>();
        Master master = null;
        master=masterDao.selectById(masterId);
        list.add(master);
        map.put("total",1);
        map.put("rows",list);
        return map;
    }

    public Boolean add(Master master) {
        Boolean flag = false;
        int i = masterDao.insert(master);
        if(i>0){
            flag = true;
        }
        return true;
    }
    public Boolean modify(Master master) {
        Boolean flag = false;
        int i = masterDao.update(master);
        if(i>0){
            flag = true;
        }
        return true;
    }
}
