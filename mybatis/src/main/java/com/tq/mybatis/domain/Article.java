package com.tq.mybatis.domain;

import javax.xml.stream.events.Comment;
import java.util.List;

public class Article {
    private Integer id;
    private String title;
    private String content;
    private String author;
    private List<Comment> commentList;//关联到该文章相关评论，所以是一个集合

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return content;

    }
}
