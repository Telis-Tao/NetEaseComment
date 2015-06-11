package com.bupt.telis.neteasecomment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Telis on 2015/6/9.
 */
public class Comment {
    private int id;
    private String content;
    private int vote;

    public List<BriefComment> getBriefComments() {
        return briefComments;
    }

    public void setBriefComments(List<BriefComment> briefComments) {
        this.briefComments = briefComments;
    }

    //    private int previous;
    private List<BriefComment> briefComments;
    private User author;
    private Post post;
    private Date createTime;
    private static Random random = new Random();

    public Comment() {
        //        this.content = content;
        this.vote = random.nextInt(400);
        //                this.previous = previous;
        this.author = new User();
        //        this.author = ClsCons.NAME[random.nextInt(ClsCons.NAME.length)];
        this.post = new Post();
        createTime = new Date();
        content = ClsCons.COMMENTS[random.nextInt(ClsCons.COMMENTS.length)];
        briefComments = new ArrayList<>();
        //        random.nextInt(4)
        for (int i = 0; i < random.nextInt(10); i++) {
            briefComments.add(new BriefComment());
        }
    }

    public Comment(String content, User author, Post post) {
        this.content = content;
        this.vote = 0;
        this.author = author;
        this.post = post;
        createTime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
