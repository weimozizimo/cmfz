package com.baizhi.cmfz.entity;

import java.util.Date;

/**
 * @Description 文章实体类
 * @Author weizimo
 * @Time 2018/7/9 9:57.
 */
public class Article {
    private String articleId;
    private String articleName;
    private Integer masterId;
    private String introduction;
    private Date date;
    private String mainPic;


    @Override
    public String toString() {
        return "Article{" +
                "articleId='" + articleId + '\'' +
                ", articleName='" + articleName + '\'' +
                ", masterId=" + masterId +
                ", introduction='" + introduction + '\'' +
                ", date=" + date +
                ", mainPic='" + mainPic + '\'' +
                '}';
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public Article() {
    }

    public Article(String articleId, String articleName, Integer masterId, String introduction, Date date, String mainPic) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.masterId = masterId;
        this.introduction = introduction;
        this.date = date;
        this.mainPic = mainPic;
    }
}
