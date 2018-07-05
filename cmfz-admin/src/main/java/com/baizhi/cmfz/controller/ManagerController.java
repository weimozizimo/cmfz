package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.service.ManagerService;
import com.baizhi.cmfz.service.MenuService;
import com.baizhi.cmfz.util.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Description
 * @Author weizimo
 * @Time ${DATA} 16:13.
 */
@Controller
public class ManagerController {
    @Autowired
    private ManagerService  ms;

    @Autowired
    private MenuService mns;

    @RequestMapping("/login")
    public String login(String mgrName, String mgrPwd,String enCode,HttpServletRequest request, HttpServletResponse response,String isRem) throws UnsupportedEncodingException {
        Manager mgr = ms.login(mgrName,mgrPwd);
        String vcode = (String) request.getSession().getAttribute("vcode");
        if(vcode.equalsIgnoreCase(enCode)&&!enCode.isEmpty()) {
            if (mgr != null) {
                request.getSession().setAttribute("manager",mgr);
                if (isRem!=null&&isRem.equals("true")) {
                    mgrName = java.net.URLEncoder.encode(mgrName, "utf-8");
                    Cookie c1 = new Cookie("mgrName", mgrName);
                    Cookie c2 = new Cookie("isRem", isRem);
                    c1.setMaxAge(10000);
                    c2.setMaxAge(10000);
                    response.addCookie(c1);
                    response.addCookie(c2);
                }
                    return "redirect:/showType";
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

    @RequestMapping("/showType")
    public String showType(Model model){
        List<Menu> menus = mns.queryMenuParentAndChild();
        model.addAttribute("menus",menus);
        return "main/main";
    }

    public ManagerService getMs() {
        return ms;
    }

    public void setMs(ManagerService ms) {
        this.ms = ms;
    }

    public MenuService getMns() {
        return mns;
    }

    public void setMns(MenuService mns) {
        this.mns = mns;
    }
}
