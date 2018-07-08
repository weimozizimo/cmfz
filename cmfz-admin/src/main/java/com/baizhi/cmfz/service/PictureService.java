package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Picture;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/5 21:35.
 */
public interface PictureService {


    public Map<String,Object> queryAllPicture(Integer pageIndex,Integer pageLine) throws UnsupportedEncodingException;

    public Boolean addPic(Picture picture);

    public Boolean editPic(Picture picture);

    public Picture queryPictureById(Integer pictureId);
}
