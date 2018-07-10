package com.baizhi.cmfz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/9 14:52.
 */
@Controller
@RequestMapping("/count")
public class CountPageController {

    @RequestMapping("/getMan")
    public @ResponseBody
    List<Object> countMan(){
        List<Object> list = new ArrayList<Object>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        map1.put("name","河南");
        map1.put("value","200");
        map2.put("name","北京");
        map2.put("value","100");
        list.add(map1);
        list.add(map2);
        return list;
    }
    @RequestMapping("/getWomen")
    public @ResponseBody
    List<Object> countWomen(){
        List<Object> list = new ArrayList<Object>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        map1.put("name","宁夏");
        map1.put("value","200");
        map2.put("name","内蒙古");
        map2.put("value","100");
        list.add(map1);
        list.add(map2);
        return list;
    }
}
