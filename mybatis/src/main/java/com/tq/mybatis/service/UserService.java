package com.tq.mybatis.service;

import com.tq.mybatis.domain.Article;
import com.tq.mybatis.domain.Comment;
import com.tq.mybatis.domain.User;
import com.tq.mybatis.mapper.ArticleMapper;
import com.tq.mybatis.mapper.CommentMapper;
import com.tq.mybatis.mapper.UserMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleMapper articleMapper;
    public User getUser(User user){
        return userMapper.findUser(user);
    }

    public List<Comment> findAll(){
        return userMapper.findAll();
    }

    public int deleteById(Integer id){
        return  userMapper.deleteCommentById(id);
    }

    @Transactional
    public int deletAll(String [] listId){
        try {
            for (String idlist: listId) {
                Integer id = Integer.parseInt(idlist);
                userMapper.deleteCommentById(id);
            }
            return 1;
        }catch (Exception ex){
            return 0;
        }
    }

    @Transactional
    public int updateComment(Comment comment){
        return commentMapper.updateCommentById(comment);
    }

    @Transactional
    public int insertArticle(Article article){
        return articleMapper.insertArticle(article);
    }

    @Transactional
    public  int saveUser(User user){
        return userMapper.saveUser(user);
    }

    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }
}
