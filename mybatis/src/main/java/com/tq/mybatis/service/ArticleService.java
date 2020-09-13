package com.tq.mybatis.service;

import com.tq.mybatis.domain.Comment;
import com.tq.mybatis.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    /**
     * 条件查询
     * @param comment
     * @return
     */
    public List<Comment> findContent(Comment comment){
        return articleMapper.findContent(comment);
    }
}
