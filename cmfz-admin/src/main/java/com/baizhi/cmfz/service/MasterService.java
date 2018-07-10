package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Master;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/8 10:21.
 */
public interface MasterService {
    public Map<String,Object> queryByName(Integer pageIndex, Integer pageLine,String masterName);

    public Map<String,Object> queryAll(Integer pageIndex, Integer pageLine);

    public List<Master> queryAll();

    public Map<String,Object> queryById(Integer masterId);

    public Boolean modify(Master master);

    public Boolean add(Master master);
}
