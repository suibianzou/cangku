package com.tq.mybatis.mapper;

import com.tq.mybatis.domain.Article;
import com.tq.mybatis.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleMapper {

    @Select("SELECT title,content FROM t_article WHERE id=#{id}")
    public Article findCommentById(Integer id);


    public Article findArticleById(Integer id);
    //插入
    public int  insertArticle(Article article);

    public int updateArticle(Article article);

    public int deleteArticleById(Integer id);

    public List<Comment> findContent(Comment comment);
}
