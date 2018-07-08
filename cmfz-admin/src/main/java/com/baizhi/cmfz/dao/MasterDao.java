package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Master;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/8 9:40.
 */
public interface MasterDao {
    public List<Master> selectByName(@Param("start") Integer start,@Param("end") Integer end,String name);

    public List<Master> selectAll(@Param("start") Integer start,@Param("end") Integer end);

    public Master selectById(Integer id);

    public Integer update(Master master);

    public Integer getCountByName(String masterName);

    public Integer getCount();
}
