package com.tq.mybatis.controller;


import com.tq.mybatis.domain.City;
import com.tq.mybatis.domain.User;
import com.tq.mybatis.mapper.EchartsMapper;
import com.tq.mybatis.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.UUID;

import static java.awt.SystemColor.info;

@Controller

public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    EchartsMapper echartsMapper;
   @Value("${uploadfilepath}")
   String uploadfilepath;

    @PostMapping("/logintest")
    public String userLogin(HttpServletRequest request, Model model, User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        User result = userService.getUser(user);
        if (result != null) {
            if (user.getPassword().equals(result.getPassword())) {
                System.out.println("登录成功!");
                request.getSession().setAttribute("loginuser", result.getUsername());
                request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
                model.addAttribute("username",user.getUsername());
                return "admin/admin_1";
            } else {
                System.out.println("用户名或密码错误!");
                model.addAttribute("msg","用户名或密码错误");
                return "logintest";
            }
        } else {
            System.out.println("该用户不存在");
            model.addAttribute("msg","该用户不存在");
            return  "logintest";
        }
    }

    @PostMapping("/admin/uploadfileregister")
    public String userRegister(MultipartFile[] fileupload, Model model, User user){
        String files="";
        if(fileupload!=null){
            for (MultipartFile file:fileupload) {
                //获取初始文件名
                String filename=file.getOriginalFilename();
                //构建新文件名
                filename= UUID.randomUUID()+"_"+filename;
                files+=filename+"$";
                //创建文件上传的路径
                File filepath=new File(uploadfilepath);
                if(!filepath.exists()){
                    filepath.mkdir();
                }
                try{
                    file.transferTo(new File(uploadfilepath+filename));
                    System.out.println("文件上传成功");

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("文件上传失败");
                }

            } user.setPic(files);
            if (userService.saveUser(user)!=0){
                System.out.println("注册成功");
            }else {
                System.out.println("注册失败");
            }
        }
        return "admin/user";
    }
    @GetMapping("showp")
    public String goPicture(Model model){
        Integer id=32;
        User user=userService.getUserById(id);
        System.out.println(user);
        model.addAttribute("user",user);
        String[] picfiles=user.getPic().split("\\$");
       // System.out.println(picfiles[1]);

        model.addAttribute("picfiles",picfiles);
        return "showp";
    }

    @GetMapping("/admin/download")
    public ResponseEntity<byte[]> fileDownload(String filename){
        System.out.println(filename);
        //return null;
        //创建文件对象
        File file = new File(uploadfilepath+File.separator+filename);
        //设置响应头
        HttpHeaders headers = new HttpHeaders();
        //解决中文乱码问题
        byte[] filenamebytes=filename.getBytes(StandardCharsets.UTF_8);//把中文文件名替换为utf_8编码
        filename = new String(filenamebytes,0,filenamebytes.length,StandardCharsets.ISO_8859_1);
        //通知浏览器以下载方式打开
        headers.setContentDispositionFormData("attchement",filename);
        //定义以流方式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            //执行下载  返回数据
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<byte[]>(e.getMessage().getBytes(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/admin/selectCity")
    public String getCity(City city, Model model){
       // System.out.println(city);
        System.out.println(echartsMapper.getCity(city));
        if (echartsMapper.getCity(city)!=null) {
            model.addAttribute("city",echartsMapper.getCity(city));

            System.out.println("查询成功");
        }else {
            System.out.println("查询失败");
        }
        return "admin/baidumap";
    }

    @GetMapping("/admin/selectCityname")
    public String getCityname(@RequestParam(value = "citys",required = false) String citys, City city, Model model){
        // System.out.println(city);
        System.out.println(citys);
        city.setCityname(citys);
        if (citys.equals(city)) {
            System.out.println("差不多了");
        }
        System.out.println(city);
        System.out.println(echartsMapper.getCity(city));
        if (echartsMapper.getCity(city)!=null) {
            model.addAttribute("city",echartsMapper.getCity(city));

            System.out.println("查询成功了？");
        }else {
            System.out.println("查询失败");
        }
        return "admin/baidumap";
    }

//    @GetMapping("/login")
//    public String getLogin(HttpServletRequest request,Model model){
//        if (request.getParameter("msg")!=null){
//            model.addAttribute("msg",request.getParameter("msg"));
//        }
//        return "logintest.html";
//   }

        @GetMapping("/login")
    public String getLogin(HttpServletRequest request,Model model){
        if (request.getParameter("msg")!=null){
            model.addAttribute("msg",request.getParameter("msg"));
        }
        return "logintest";
   }
}
