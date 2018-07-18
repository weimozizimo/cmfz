package com.baizhi.cmfz.controller;


import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.ManagerService;

import com.baizhi.cmfz.service.MenuService;
import com.baizhi.cmfz.service.PictureService;
import com.baizhi.cmfz.util.FastDfs;
import com.baizhi.cmfz.util.HandleMtpFileSize;
import com.baizhi.cmfz.util.ValidateImageCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * @Description
 * @Author weizimo
 * @Time ${DATA} 16:13.
 */
@Controller
public class ManagerController {
    @Autowired
    private ManagerService ms;

    @Autowired
    private MenuService mns;

    @Autowired
    private PictureService pcs;


    @RequestMapping("/login")
    public String login(String mgrName, String mgrPwd, String enCode, HttpServletRequest request, HttpServletResponse response, Boolean isRem) throws UnsupportedEncodingException {
        //在web环境下安全管理器会自动进行初始化
        Subject subject = SecurityUtils.getSubject();
        String vcode = (String) request.getSession().getAttribute("vcode");
        if (vcode.equalsIgnoreCase(enCode) && !enCode.isEmpty()) {
            try {
                if(isRem==null){
                    isRem=false;
                }
                subject.login(new UsernamePasswordToken(mgrName, mgrPwd,isRem));
                request.getSession().setAttribute("manager", ms.queryMgr(mgrName));
                return "redirect:/main/main.jsp";
            } catch (IncorrectCredentialsException ice) {
                System.out.println("密码错误");
                ice.printStackTrace();
                return "login";
            } catch (AuthenticationException e) {
                System.out.println("认证异常");
                e.printStackTrace();
                return "login";
            }
        }
        return "login";
    }



    @RequestMapping("/vcode")
    public void login(HttpServletResponse response,HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        String vcode = ValidateImageCodeUtils.getSecurityCode();

        session.setAttribute("vcode",vcode);

        BufferedImage image = ValidateImageCodeUtils.createImage(vcode);

        ImageIO.write(image, "png", response.getOutputStream());
    }

    @RequestMapping("/menu")
    public @ResponseBody List<Menu>  showType( Model model){
        List<Menu> menus = mns.queryMenuParentAndChild();
        return menus;
    }
    @RequestMapping("slideShow")
    public @ResponseBody
    //total rows
    Map<String,Object> showPicMsg(@RequestParam("page")Integer pageIndex,@RequestParam("rows")Integer pageLine) throws UnsupportedEncodingException {
        return pcs.queryAllPicture(pageIndex,pageLine);
    }
    @RequestMapping("addPic")
    public @ResponseBody
    Map<String,String> addPic(String status, String dec,HttpSession session,@RequestParam("file") MultipartFile myfile) throws IOException, MyException {
        Picture pic = new Picture();
        String suffix = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf("."));
        String[] str = FastDfs.client.upload_file(myfile.getBytes(),suffix,new NameValuePair[]{new NameValuePair("width", HandleMtpFileSize.getHeight(myfile))});
        pic.setPictureName(str[0]+"/"+str[1]);
        pic.setPictureStatus(status);
        pic.setPictureDescription(dec);
        pic.setPictureDate(new Date());
        Boolean b = pcs.addPic(pic);
        Map<String, String> map = new HashMap<String, String>();
        if(b){
            map.put("result","success");
        }else{
            map.put("result","fail");
        }
        return map;
    }
    @RequestMapping("editPic")
    public @ResponseBody
    Map<String,String> editPic(Picture pic,HttpSession session,@RequestParam("file") MultipartFile myfile) throws IOException, MyException {
        String suffix = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf("."));
        String[] str = FastDfs.client.upload_file(myfile.getBytes(),suffix,new NameValuePair[]{new NameValuePair("width", HandleMtpFileSize.getHeight(myfile))});
        Map<String, String> map = new HashMap<String, String>();
        if(myfile.getSize()!=0){
            pic.setPictureName(str[0]+"/"+str[1]);
            Boolean b = pcs.modifyPic(pic);
            if(b){
                  map.put("result", "success");
            }else {
                map.put("result", "fail");
            }
        }else{
            Boolean b = pcs.modifyPic(pic);
            if(b){
                map.put("result", "success");
            }else {
                map.put("result", "fail");
            }
        }
        return map;
    }

}
