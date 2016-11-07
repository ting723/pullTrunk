package com.zhanglw.svnTools.util;

import com.zhanglw.svnTools.bean.AuthenBean;

/**
 * Created by zhanglw on 2016/7/10.
 */
public class Constant {

    public static AuthenBean authenBean = new AuthenBean();

    public static String key = ConfUtil.getConfigBean( "config.yaml" ).getEncryptionKey();

    static {
        authenBean = ConfUtil.getBean( AuthenBean.class );
        try {
            if (authenBean != null) {
                String username = QEncodeUtil.aesDecrypt( authenBean.getUsername(), key );
                String password = QEncodeUtil.aesDecrypt( authenBean.getPassword(), key );
                String serial = QEncodeUtil.aesDecrypt( authenBean.getSerial(), key );
                authenBean.setPassword( password );
                authenBean.setSerial( serial );
                authenBean.setUsername( username );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println( Constant.authenBean.getUsername() );
    }
}
