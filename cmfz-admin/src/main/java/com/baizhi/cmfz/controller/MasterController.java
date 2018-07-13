package com.baizhi.cmfz.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.baizhi.cmfz.dao.MasterDao;
import com.baizhi.cmfz.entity.Master;
import com.baizhi.cmfz.service.MasterService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/8 10:42.
 */
@Controller
@RequestMapping("/master")
public class MasterController {
    @Autowired
    private MasterService ms;
    @RequestMapping("/showMaster")
    public @ResponseBody
    Map<String,Object> showMaster(@RequestParam("page") Integer pageIndex, @RequestParam("rows") Integer pageLine){
        return ms.queryAll(pageIndex, pageLine);
    }

    @RequestMapping("/addMaster")
    public @ResponseBody
    Map<String,String> addMaster(Master master, HttpSession session,@RequestParam("file") MultipartFile myfile) throws IOException {
        String oldName = myfile.getOriginalFilename();
        String uuidName = UUID.randomUUID().toString().replace("-","");
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        String Path = session.getServletContext().getRealPath("");
        String loadPath = Path.substring(0,Path.lastIndexOf("\\"))+"\\upload";
        master.setMasterPhoto(uuidName+suffix);
        Boolean b = ms.add(master);
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

    @RequestMapping("/editMaster")
    public @ResponseBody
    Map<String,String> editMaster(Master master,HttpSession session,@RequestParam("file")MultipartFile myfile) throws IOException {
        String Path = session.getServletContext().getRealPath("");
        String loadPath = Path.substring(0,Path.lastIndexOf("\\"))+"\\upload";
        Map<String, String> map = new HashMap<String, String>();
        if(myfile.getSize()!=0){
            System.out.println(myfile.getName());
            String oldName = myfile.getOriginalFilename();
            String uuidName = UUID.randomUUID().toString().replace("-","");
            String suffix = oldName.substring(oldName.lastIndexOf("."));
            String newName = uuidName+suffix;
            master.setMasterPhoto(newName);
            Boolean b = ms.modify(master);
            if(b){
                myfile.transferTo(new File(loadPath+"/"+newName));
                map.put("result", "success");
            }else {
                map.put("result", "fail");
            }
        }else{
            Boolean b = ms.modify(master);
            if(b){
                map.put("result", "success");
            }else {
                map.put("result", "fail");
            }
        }
        return map;
    }
    @RequestMapping("/queryByName")
    public @ResponseBody
    Map<String,Object> queryByName(String masterName,@RequestParam("page") Integer pageIndex, @RequestParam("rows") Integer pageLine){
        return ms.queryByName(pageIndex,pageLine,masterName);
    }
    @RequestMapping("/queryById")
    public @ResponseBody
    Map<String,Object> queryById(String masterId,@RequestParam("page") Integer pageIndex, @RequestParam("rows") Integer pageLine){
        return ms.queryById(Integer.parseInt(masterId));
    }
    @RequestMapping("/addList")
    public @ResponseBody
    Map<String,String> addList(MultipartFile file,HttpSession session) throws IOException {
        String Path = session.getServletContext().getRealPath("");
        String loadPath = Path.substring(0,Path.lastIndexOf("\\"))+"\\upload";
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        long start = new Date().getTime();
        File ExcelFile = new File(loadPath+"/"+file.getOriginalFilename());
        file.transferTo(ExcelFile);
        List<Master> masterList = ExcelImportUtil.importExcel(ExcelFile,Master.class,params);
        for (int i = 0; i < masterList.size(); i++) {

            File file1 = new File(Path+"/"+masterList.get(i).getMasterPhoto());
            masterList.get(i).setMasterPhoto(file1.getName());
            boolean b = ms.add(masterList.get(i));
            if(b){
                String fileName = file1.getName();
                file1.renameTo(new File(loadPath + "/" + fileName));
                System.out.println("成功插入"+masterList.get(i).getMasterName());
            }
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("result","success");
        return map;
    }

    @RequestMapping("/exportMaster")
    public void exportExcel(HttpServletResponse resp,HttpSession session) throws IOException {
        List<Master> masters = ms.queryAll();
        String Path = session.getServletContext().getRealPath("");
        String loadPath = Path.substring(0,Path.lastIndexOf("\\"))+"\\upload";
//        for (Master m: masters
//             ) {
//            m.setMasterPhoto(loadPath+"\\"+m.getMasterPhoto());
//        }
        //excel文件
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("weizimo","上师信息表"),Master.class,masters);

        ServletOutputStream out = resp.getOutputStream();


        //文件下载 设置响应头
        resp.setContentType("application/vnd.ms-excel");
        //响应头 默认使用编码格式iso-8859-1
        String fileName = new String("上师信息表.xls".getBytes(),"iso-8859-1");
        resp.setHeader("context-disposition","attachment;filename="+fileName);
        //导出 文件下载的方法
        workbook.write(out);
        out.close();
    }
}
