package com.baizhi.cmfz.controller;


import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.ManagerService;

import com.baizhi.cmfz.service.MenuService;
import com.baizhi.cmfz.service.PictureService;
import com.baizhi.cmfz.util.ValidateImageCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    Map<String,String> addPic(String status, String dec,HttpSession session,@RequestParam("file") MultipartFile myfile) throws IOException {
        Picture pic = new Picture();
        String oldName = myfile.getOriginalFilename();
        String uuidName = UUID.randomUUID().toString().replace("-","");
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        pic.setPictureStatus(status);
        pic.setPictureDescription(dec);
        pic.setPictureDate(new Date());
        pic.setPictureName(myfile.getOriginalFilename());
        String Path = session.getServletContext().getRealPath("");
        String loadPath = Path.substring(0,Path.lastIndexOf("\\"))+"\\upload";
        pic.setPictureName(uuidName+suffix);
        Boolean b = pcs.addPic(pic);
        Map<String, String> map = new HashMap<String, String>();
        if(b) {
            myfile.transferTo(new File(loadPath+"/"+uuidName+suffix));
            map.put("result", "success");
        }else{
            map.put("result", "fail");
        }

        map.put("result", "success");
        return map;
    }
    @RequestMapping("editPic")
    public @ResponseBody
    Map<String,String> editPic(Picture pic,HttpSession session,@RequestParam("file") MultipartFile myfile) throws IOException {
        String Path = session.getServletContext().getRealPath("");
        String loadPath = Path.substring(0,Path.lastIndexOf("\\"))+"\\upload";
        Map<String, String> map = new HashMap<String, String>();
        if(myfile.getSize()!=0){
            String oldName = myfile.getOriginalFilename();
            String uuidName = UUID.randomUUID().toString().replace("-","");
            String suffix = oldName.substring(oldName.lastIndexOf("."));
            String newName = uuidName+suffix;
            pic.setPictureName(newName);
            Boolean b = pcs.modifyPic(pic);
            if(b){
                  myfile.transferTo(new File(loadPath+"/"+newName));
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
