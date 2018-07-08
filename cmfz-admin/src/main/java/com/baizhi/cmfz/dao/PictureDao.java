package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/5 20:52.
 */
public interface PictureDao {
    public List<Picture> fintAllPicture(@Param("start")Integer start, @Param("end") Integer end);

    public Integer getCount();

    public Integer insert(Picture picture);

    public Integer update(Picture picture);

    public Picture selectPicture(Integer pictureId);
 }
