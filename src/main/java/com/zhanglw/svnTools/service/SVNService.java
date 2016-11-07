package com.zhanglw.svnTools.service;

/**
 * Created by zhanglw on 2016/7/10.
 */
public interface SVNService {

   boolean isExist(String svnUrl);

   String copyToTrunk(String testUrl,String trunkUrl,String message);

   boolean delDir(String trunkUrl,String message);
}
