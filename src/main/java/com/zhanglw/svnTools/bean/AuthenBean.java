package com.zhanglw.svnTools.bean;

import java.io.Serializable;

/**
 * Created by zhanglw on 2016/7/10.
 */
public class AuthenBean implements Serializable {

    private String username;

    private String password;

    private String serial;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
