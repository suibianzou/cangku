package com.tq.mybatis.mapper;

import com.tq.mybatis.domain.Comment;
import com.tq.mybatis.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper

public interface UserMapper {

    @Select("SELECT * FROM t_user WHERE username=#{username}")
    public User findUser(User user);

    @Select("SELECT * FROM t_comment")
    public List<Comment> findAll();

    @Delete("DELETE FROM  t_comment WHERE id=#{id}")
    public int deleteCommentById(Integer id);
    @Insert("INSERT INTO t_users (username,password,email,pic) VALUES(#{username},#{password},#{email},#{pic})")
    public int saveUser(User user);

    @Select("SELECT * FROM t_users WHERE id=#{id}")
    public User getUserById(Integer id);
}
