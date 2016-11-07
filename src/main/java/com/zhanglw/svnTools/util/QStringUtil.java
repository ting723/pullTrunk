package com.zhanglw.svnTools.util;

/**
 * Created by zhanglw on 2016/7/13.
 */
public class QStringUtil {


    public static boolean isEmpty(String encryptStr) {
        if(encryptStr==null || encryptStr.length()==0){
            return true;
        }
        return false;
    }
}
