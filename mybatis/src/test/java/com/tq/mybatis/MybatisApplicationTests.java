package com.tq.mybatis;

import com.tq.mybatis.domain.Article;
import com.tq.mybatis.domain.Comment;
import com.tq.mybatis.mapper.ArticleMapper;
import com.tq.mybatis.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Autowired
////    private CommentMapper commentMapper;
////
////    @Test
////    public void testFindComment() {
////        try {
////            Comment comment = commentMapper.findCommentById(1);
////            System.out.println(comment);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }

    @Autowired
    private ArticleMapper articleMapper;
    @Test
    public void FindCommentTest(){
        try {
            Article article = articleMapper.findArticleById(1);
            System.out.println(article);
            System.out.println("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
