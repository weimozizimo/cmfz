package com.baizhi.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @Description 上师信息的实体类
 * @Author weizimo
 * @Time 2018/7/8 9:40.
 */
public class Master {
    @Excel(name="上师编号",isImportField = "true")
   private Integer masterId;
    @Excel(name="上师法号",isImportField = "true")
   private String masterName;
    @Excel(name="上师头像",isImportField = "true",type=2,imageType = 1)
   private String masterPhoto;
    @Excel(name="上师简介",isImportField = "true")
   private String masterSummary;

    @Override
    public String toString() {
        return "Master{" +
                "masterId=" + masterId +
                ", masterName='" + masterName + '\'' +
                ", masterPhoto='" + masterPhoto + '\'' +
                ", masterSummary='" + masterSummary + '\'' +
                '}';
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterPhoto() {
        return masterPhoto;
    }

    public void setMasterPhoto(String masterPhoto) {
        this.masterPhoto = masterPhoto;
    }

    public String getMasterSummary() {
        return masterSummary;
    }

    public void setMasterSummary(String masterSummary) {
        this.masterSummary = masterSummary;
    }

    public Master() {
    }

    public Master(Integer masterId, String masterName, String masterPhoto, String masterSummary) {
        this.masterId = masterId;
        this.masterName = masterName;
        this.masterPhoto = masterPhoto;
        this.masterSummary = masterSummary;
    }
}
