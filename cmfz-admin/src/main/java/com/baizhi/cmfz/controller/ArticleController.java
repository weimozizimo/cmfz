package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Master;
import com.baizhi.cmfz.service.ArticleService;
import com.baizhi.cmfz.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/9 15:35.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private MasterService ms;
    @Autowired
    private ArticleService as;

    @RequestMapping("/getMaster")
    public @ResponseBody
    List<Master> m1(){
        return ms.queryAll();
    }
}
