package com.bupt.telis.neteasecomment;

import java.util.UUID;

/**
 * Created by Telis on 2015/6/9.
 */
public class User {
    public static final String COLUMN_ID = "_id", COLUMN_NAME = "name", COLUMN_ICON = "voted",
            COLUMN_LOCALE = "locale", COLUMN_TITLE = "title";
    private String id;
    private String name;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    private int icon;
    private String locale;
    private String title;


    public User() {
        id = UUID.randomUUID().toString();
        this.name = ClsCons.NAME[(int) (Math.random() * 10)];
        this.locale = ClsCons.LOCALE[(int) (Math.random() * 5)];
        this.icon = ClsCons.ICON[(int) (Math.random() * 3)];
        //        this.nick = ClsCons.DEFAULT_NICK_RESID[(int) (Math.random() * 3)];
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
