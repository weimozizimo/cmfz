package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.proxy.LogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/10 10:23.
 */
@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService ls;

    @RequestMapping("showLog")
    public @ResponseBody
    Map<String,Object> m1(@RequestParam("page")Integer pageIndex, @RequestParam("rows")Integer pageLine){
        return ls.queryAllLog(pageIndex,pageLine);
    }
}
