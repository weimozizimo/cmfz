package com.baizhi.cmfz.proxy;

import com.baizhi.cmfz.dao.LogDao;
import com.baizhi.cmfz.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/10 10:28.
 */
@Service
@Transactional
public class LogServiceImpl implements  LogService{

    @Autowired
    private LogDao ld;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryAllLog(Integer pageIndex, Integer pageLine) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Integer count = ld.countLog();
        List<Log> logs = ld.findAllLog((pageIndex-1)*pageLine,pageLine);
        map.put("total",count);
        map.put("rows",logs);
        return map;
    }
}
