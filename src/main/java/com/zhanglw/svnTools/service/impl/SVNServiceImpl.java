package com.zhanglw.svnTools.service.impl;

import com.zhanglw.svnTools.service.SVNService;
import com.zhanglw.svnTools.util.Constant;
import com.zhanglw.svnTools.util.SVNUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;

/**
 * Created by zhanglw on 2016/7/10.
 */
public class SVNServiceImpl implements SVNService {

    private final Logger logger = LogManager.getLogger( SVNServiceImpl.class );

    public static void main(String[] args) {

    }

    public boolean isExist(String url) {
        boolean flag = false;
        try {
            flag = SVNUtil.isURLExist( SVNURL.parseURIEncoded( url ), Constant.authenBean.getUsername(), Constant.authenBean.getPassword() );
            logger.info( "验证路径成功!" );
        } catch (SVNException e) {
            e.printStackTrace();
            logger.error( "验证路径失败!",e );
        }
        return flag;
    }

    public String copyToTrunk(String testUrl, String trunkUrl, String message) {
        if (message == null || message.length() == 0) {
            message = "Copy the version";
        }
        String flag = "";
        if (isExist( testUrl )) {
            try {
                SVNCommitInfo svnCommitInfo = SVNUtil.copy( SVNURL.parseURIEncoded( testUrl ), SVNURL.parseURIEncoded( trunkUrl ), false, message, Constant.authenBean.getUsername(), Constant.authenBean.getPassword() );
                flag = svnCommitInfo.toString();
                logger.info( "Copy SVN Url 成功!" );
            } catch (SVNException e) {
                e.printStackTrace();
                flag = "Copy Fail";
                logger.error( "svn 路径copy失败！", e );
                return flag;
            }
        }
        return flag;
    }


    public boolean delDir(String trunkUrl, String message) {
        if (message == null || message.length() == 0) {
            message = "Delete This version";
        }
        try {
            SVNUtil.delete( SVNURL.parseURIEncoded( trunkUrl ), message, Constant.authenBean.getUsername(), Constant.authenBean.getPassword() );
            logger.info( "Delete 已存在目录" );
        } catch (SVNException e) {
            e.printStackTrace();
        }
        return false;
    }
}
