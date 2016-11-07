package com.zhanglw.svnTools.bean;

import java.io.Serializable;

/**
 * Created by zhanglianwei on 2016/11/7.
 */
public class ConfigBean implements Serializable{

    private String testSVNUrl;

    private String encryptionKey;

    public String getTestSVNUrl() {
        return testSVNUrl;
    }

    public void setTestSVNUrl(String testSVNUrl) {
        this.testSVNUrl = testSVNUrl;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
}
