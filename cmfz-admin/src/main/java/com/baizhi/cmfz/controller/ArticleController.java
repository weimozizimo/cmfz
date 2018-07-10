package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.entity.Master;
import com.baizhi.cmfz.service.ArticleService;
import com.baizhi.cmfz.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    @RequestMapping("/addArticle")
    public @ResponseBody
    Map<String,String> m2(Article article, @RequestParam("main_pic")MultipartFile myfile, HttpSession session) throws IOException {
        article.setDate(new Date());
        article.setArticleId(UUID.randomUUID().toString().replace("-",""));
        if(myfile.getSize()!=0) {
            String oldName = myfile.getOriginalFilename();
            String uuidName = UUID.randomUUID().toString().replace("-", "");
            String suffix = oldName.substring(oldName.lastIndexOf("."));
            String Path = session.getServletContext().getRealPath("");
            String loadPath = Path.substring(0,Path.lastIndexOf("\\"))+"\\upload";
            myfile.transferTo(new File(loadPath+"\\"+uuidName+suffix));
            article.setMainPic(uuidName);
        }
        Boolean b = as.addArticle(article);
        HashMap<String, String> map = new HashMap<String, String>();
        if(b){
            map.put("result","success");
        }else {
            map.put("result","fail");
        }

        return map;
    }
}
