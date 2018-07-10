package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.PictureDao;
import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/5 21:49.
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    private  PictureDao pictureDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryAllPicture(Integer pageIndex, Integer pageLine) throws UnsupportedEncodingException {
        List<Picture> pics = pictureDao.fintAllPicture((pageIndex-1)*pageLine,pageLine);
        for(Picture pic:pics){
            if(pic.getPictureStatus().equals("1")){
                pic.setPictureStatus(new String("展示中".getBytes("utf-8"),"UTF-8"));
            }else{
                pic.setPictureStatus(new String("未展示".getBytes("utf-8"),"UTF-8"));
            }
        }
        Integer count = pictureDao.getCount();
        Map<String,Object> maps = new HashMap<String, Object>();
        maps.put("rows",pics);
        maps.put("total",count);

        return maps;
    }

    public Boolean addPic(Picture picture) {
        Boolean flag=false;
        int i = pictureDao.insert(picture);
        if(i>0){
            flag=true;
        }
        return flag;
    }

    public Boolean modifyPic(Picture picture) {
        Boolean flag=false;
        int i = pictureDao.update(picture);
        if(i>0){
            flag=true;
        }
        return flag;
    }

    public Picture queryPictureById(Integer pictureId) {
        Picture pic = pictureDao.selectPicture(pictureId);
        return pic;
    }

    public PictureDao getPictureDao() {
        return pictureDao;
    }

    public void setPictureDao(PictureDao pictureDao) {
        this.pictureDao = pictureDao;
    }


}
