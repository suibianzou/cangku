package com.tq.mybatis.mapper;

import com.tq.mybatis.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper//@Component,Configuration,Controller,Repository
public interface CommentMapper {

    @Select("SELECT * FROM t_comment WHERE id=#{id}")
    public Comment findCommentById(Integer id);

    @Insert("INSERT INTO t_comment(content,author,a_id) values(#{content},#{author},#{aId})")
    public int  insertComment(Comment comment);

    @Update("UPDATE t_comment SET content=#{content} ,author=#{author},a_id=#{aId} WHERE id=#{id}")
    public int updateComment(Comment comment);

    @Delete("DELETE FROM  t_comment WHERE id=#{id}")
    public int deleteCommentById(Integer id);

    @Select("SELECT * FROM t_comment WHERE a_id=#{aid}")
    public List<Comment> findByAid(Integer aid);

    @Select("SELECT * FROM t_comment")
    public List<Comment> findAll();

    public int updateCommentById(Comment comment);

}


