package com.bupt.telis.neteasecomment;

import java.util.Random;

/**
 * Created by Telis on 2015/6/9.
 */
public class BriefComment {
    private String name;
    private Random random = new Random();

    public BriefComment() {
        this.name = ClsCons.NAME[random.nextInt(ClsCons.NAME.length)];
        this.content = ClsCons.COMMENTS[random.nextInt(ClsCons.COMMENTS.length)];
    }

    public BriefComment(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String content;
}
