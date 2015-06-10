package com.bupt.telis.neteasecomment;

import java.util.Date;
import java.util.List;

/**
 * Created by Telis on 2015/6/9.
 */
public class Post {
    private int id;
    private String title;
    private User author;
    private Date time;

    public Post() {
    }

    public Post(String title, User author) {
        this.title = title;
        this.author = author;
        this.time = new Date();
    }

    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
