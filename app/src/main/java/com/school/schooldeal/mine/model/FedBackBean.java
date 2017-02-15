package com.school.schooldeal.mine.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by U-nookia on 2017/2/15.
 */

public class FedBackBean extends BmobObject{
    private String system;
    private String content;
    private BmobUser user;

    public FedBackBean(String content, String system, BmobUser user) {
        this.content = content;
        this.system = system;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public String getSystem() {
        return system;
    }

    public BmobUser getUser() {
        return user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }
}
