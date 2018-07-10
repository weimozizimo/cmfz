package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Article;

/**
 * @Description 文章数据库操作接口类
 * @Author weizimo
 * @Time 2018/7/9 10:08.
 */
public interface ArticleDao {

    /**
     * @Description 插入文章信息
     * @Param article：要插入文章的实体类(Article)
     */
    public Integer insert(Article article);

}
