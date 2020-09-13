package com.tq.mybatis.domain;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tq.mybatis.mapper.ArticleMapper;
import com.tq.mybatis.mapper.CommentMapper;
import com.tq.mybatis.service.ArticleService;
import com.tq.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
public class MyControll {
    @Value("$(uploadfilepath)")
    private String uploadfilepath;

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserService userService;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleService articleService;

    @GetMapping("/MyComment")
    public String MyComment(Model model){
        Integer id=2;
        Comment commetn=commentMapper.findCommentById(id);
        model.addAttribute("comment",commetn);
        return "comment";
    }

    @GetMapping("/getContent")
    public String getContent(Model model){
        Integer aid=2;
        List<Comment> commentList=commentMapper.findByAid(aid);
        model.addAttribute("commentList",commentList);
        return "commentList";
    }
    @GetMapping("/testlogin")
    public String testLogin(Model model){
        return "logintest";
    }

    @GetMapping("/loginout")
    public String logintOut(HttpServletRequest request){
        System.out.println("退出登录");
        request.getSession().removeAttribute("loginuser");
        return "logintest";
    }

    @GetMapping("/admin/comment")
    public String getCommentList(Model model,String startPage){

        Integer startp;
        if (startPage==null){
            startp=1;
        }else {
            startp = Integer.parseInt(startPage);
        }
        Integer pageSize=5;
        System.out.println("获取数据");
        //增加分页功能
        PageHelper.startPage(startp,pageSize);
        List<Comment> commentList=commentMapper.findAll();
        PageInfo<Comment> pageInfo=new PageInfo<Comment>(commentList);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("commentlist",commentList);
        return "admin/comment";
    }

    @ResponseBody
    @PostMapping("/admin/deletComment")
    public String deletComment(Integer id){
        System.out.println(id);
        if(commentMapper.deleteCommentById(id)!=0){
            return "ok";
        }else {
            return "error";
        }
    }

    @ResponseBody
    @PostMapping("/admin/deletAll")
    public String deletAll(@RequestParam("checkList[]") String [] checkList) {
        if (userService.deletAll(checkList) == 1) {
            System.out.println(checkList);
            return "ok";
        } else {
            return "error";
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/getEditComment")
    public Comment getEditComment(Integer id){
        System.out.println(id);

        return commentMapper.findCommentById(id);
        //return "comment";
    }

    /**
     *
     * @param comment
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/updateComment")
    public String updateComment(Comment comment){
        System.out.println("作者"+comment.getContent());
        System.out.println(userService.updateComment(comment));
        if (userService.updateComment(comment)!=0) {
            return "ok";
        }
        return "j";
    }

    /**
     * 保存提交的数据
     */
    //@ResponseBody 返回值为字符
    @PostMapping("/admin/saveArticle")
    public String saveArticleData(Model model,Article article){
        //System.out.println(article);
       // System.out.println(articleMapper.insertArticle(article));
        try {
            if (userService.insertArticle(article)!=0){
                System.out.println(article);
//                System.out.println("发表成功");
//                model.addAttribute("msg","发表成功");
                model.addAttribute("article1",article);
                return "admin/show";
            }else {
                model.addAttribute("msg","文章发表失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg","文章发表失败");
        }
        return "admin/article";
    }

    @GetMapping("/admin/findContent")
    public String findContent(Comment comment,Model model,
                        @RequestParam(value="startPage",required=false,defaultValue="1") Integer pageNum){
       try {
           int pageSize = 5;
           System.out.println(comment);
           PageHelper.startPage(pageNum, pageSize);//分页
           List<Comment> list = articleService.findContent(comment);
           PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
           model.addAttribute("pageInfo", pageInfo);
           model.addAttribute("comment", comment);
       }catch (Exception e){
           e.printStackTrace();
       }
        return "admin/comment";
    }

}
