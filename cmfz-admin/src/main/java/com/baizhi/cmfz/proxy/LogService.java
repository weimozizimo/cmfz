package com.baizhi.cmfz.proxy;

import java.util.Map;

/**
 * @Description 日志操作业务层接口类
 * @Author weizimo
 * @Time 2018/7/10 10:24.
 */
public interface LogService {
    public Map<String,Object> queryAllLog(Integer pageIndex, Integer pageLine);
}
