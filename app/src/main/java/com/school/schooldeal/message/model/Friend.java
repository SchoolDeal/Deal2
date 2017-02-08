package com.school.schooldeal.message.model;

/**
 * Created by U-nookia on 2017/2/8.
 */

public class Friend {
    private String id;
    private String name;
    private String img;

    public Friend(String id, String img, String name) {
        this.id = id;
        this.img = img;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
