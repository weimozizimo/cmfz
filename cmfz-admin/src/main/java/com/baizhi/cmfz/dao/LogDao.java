package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 日志数据库操作接口类
 * @Author weizimo
 * @Time 2018/7/9 20:05.
 */
public interface LogDao {
    public Integer insertLog(Log log);

    public List<Log> findAllLog(@Param("start") Integer start,@Param("end") Integer end);

    public Integer countLog();
}
