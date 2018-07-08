package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.dao.MasterDao;
import com.baizhi.cmfz.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/8 10:42.
 */
@Controller
@RequestMapping("/master")
public class MasterController {
    @Autowired
    private MasterService ms;
    @RequestMapping("/showMaster")
    public @ResponseBody
    Map<String,Object> showMaster(@RequestParam("page") Integer pageIndex, @RequestParam("rows") Integer pageLine){
        return ms.queryAll(pageIndex, pageLine);
    }
}
