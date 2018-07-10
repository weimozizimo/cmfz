package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Article;

/**
 * @Description 文章操作业务层接口
 * @Author weizimo
 * @Time 2018/7/9 10:26.
 */
public interface ArticleService {
    /**
     * @Description
     * @Author weizimo
     * @Time 2018/7/9 10:26.
     * @Param article:文章的实体类对象（Article)
     */
    public Boolean addArticle(Article article);
}
