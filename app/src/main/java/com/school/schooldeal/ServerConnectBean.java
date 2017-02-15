package com.school.schooldeal;

import java.io.Serializable;

/**
 * Created by U-nookia on 2017/2/15.
 */

public class ServerConnectBean implements Serializable {
    private static final long serialVersionUID = -5240152692961888097L;
    private String id;
    private String name;
    private String url;

    public ServerConnectBean(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
