package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.ArticleDao;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description ArticleService的实现类
 * @Author weizimo
 * @Time 2018/7/9 10:25.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleDao ad;

    public Boolean addArticle(Article article) {
        Boolean flag = false;
        int i = ad.insert(article);
        if(i>0){
            flag = true;
        }
        return flag;
    }
}
