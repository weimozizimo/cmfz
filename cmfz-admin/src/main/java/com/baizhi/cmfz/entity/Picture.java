package com.baizhi.cmfz.entity;

import java.util.Date;
import java.util.UUID;

/**
 * @Description 轮播图信息实体类
 * @Author weizimo
 * @Time 2018/7/5 20:47.
 */
public class Picture {
    private Integer pictureId;
    private String  pictureName;
    private Date    pictureDate;
    private String  pictureDescription;
    private String  pictureStatus;

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", pictureName='" + pictureName + '\'' +
                ", pictureDate=" + pictureDate +
                ", pictureDescription='" + pictureDescription + '\'' +
                ", pictureStatus='" + pictureStatus + '\'' +
                '}';
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public Date getPictureDate() {
        return pictureDate;
    }

    public void setPictureDate(Date pictureDate) {
        this.pictureDate = pictureDate;
    }

    public String getPictureDescription() {
        return pictureDescription;
    }

    public void setPictureDescription(String pictureDescription) {
        this.pictureDescription = pictureDescription;
    }

    public String getPictureStatus() {
        return pictureStatus;
    }

    public void setPictureStatus(String pictureStatus) {
        this.pictureStatus = pictureStatus;
    }

    public Picture(Integer pictureId, String pictureName, Date pictureDate, String pictureDescription, String pictureStatus) {
        this.pictureId = pictureId;
        this.pictureName = pictureName;
        this.pictureDate = pictureDate;
        this.pictureDescription = pictureDescription;
        this.pictureStatus = pictureStatus;
    }

    public Picture() {
    }
}
