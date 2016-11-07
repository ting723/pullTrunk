package com.zhanglw.svnTools.util;

import com.zhanglw.svnTools.bean.AuthenBean;
import com.zhanglw.svnTools.bean.ConfigBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by zhanglw on 2016/7/10.
 */

public class ConfUtil {

    private static Logger logger = LogManager.getLogger("My Logger");

    private static String userConfPath = System.getProperty("user.home") + File.separator + "AppData"
            + File.separator + "Local" + File.separator + "PullTrunk";

    private static String confFilename = "mydat";

    static {
        confFilename = userConfPath + File.separator + confFilename;
    }

    public static <T> T getBean(Class<T> c) {
        T t = null;
        try {
            File file = new File( confFilename );
            if(file.exists()){
                t = Yaml.loadType(new File(confFilename), c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return t;
    }



    public static <T> T getBean(Class<T> c,String fileName) {
        T t = null;
        try {
            t = Yaml.loadType(new File(fileName), c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static AuthenBean aesEncryptBean(String username, String pwd, String cpuserial) {
        AuthenBean authenBean = new AuthenBean();
        try {
            authenBean.setPassword(QEncodeUtil.aesEncrypt(pwd, Constant.key));
            authenBean.setUsername(QEncodeUtil.aesEncrypt(username, Constant.key));
            authenBean.setSerial(QEncodeUtil.aesEncrypt(cpuserial, Constant.key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenBean;
    }

    public static boolean check() {
        File file = new File(userConfPath + File.separator + "mydat");
        if (file.exists()) {
            AuthenBean authenBean = ConfUtil.getBean(AuthenBean.class);
            try {
                String username = QEncodeUtil.aesDecrypt(authenBean.getUsername(), Constant.key);
                String pwd = QEncodeUtil.aesDecrypt(authenBean.getPassword(), Constant.key);
                return SVNUtil.isConnect(getConfigBean("config.yaml").getTestSVNUrl(), username, pwd.toCharArray());
            } catch (Exception e) {
                e.printStackTrace();
                logger.info( "连接SVN失败,无法联网或密码错误!" );
                return  false;
            }
        }
        return false;
    }


    public static void createDat(AuthenBean authenBean) {
        try {
            File dir = new File(userConfPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(confFilename);

            if (!file.exists()) {
                file.createNewFile();
            }
            Yaml.dump(authenBean, file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigBean getConfigBean(String filename){
        String rootPath = ConfUtil.class.getClass().getResource( "/" ).getPath();
        ConfigBean configBean = ConfUtil.getBean( ConfigBean.class,rootPath+filename );

        return configBean;
    }

    public static void main(String[] args) {
//        System.out.println(userConfPath);
//        AuthenBean authenBean = new AuthenBean();
//        authenBean.setUsername("zzzz");
//        authenBean.setPassword("AbCd134");
//        authenBean.setSerial(SerialUtil.getCPUSerial());
//        createDat(authenBean);
        ConfigBean bean = getConfigBean( "config.yaml" );
        System.out.println(bean.getEncryptionKey()+bean.getTestSVNUrl());
    }
}
