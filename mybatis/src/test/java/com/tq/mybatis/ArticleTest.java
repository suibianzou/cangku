package com.tq.mybatis;

import com.tq.mybatis.domain.Article;
import com.tq.mybatis.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleTest {

    @Autowired
    ArticleMapper articleMapper;
    @Test
    public void testFindArticleById(){
        try {
            Integer id=1;
            System.out.println(articleMapper.findArticleById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testInsertArticle(){
        Article article = new Article();
        article.setTitle("ssm框架");
        article.setContent("一个Java框架");
        article.setAuthor("许杨");
        articleMapper.insertArticle(article);
        System.out.println("插入真确");
    }
}
