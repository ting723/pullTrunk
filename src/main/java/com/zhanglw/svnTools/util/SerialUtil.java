package com.zhanglw.svnTools.util;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by zhanglw on 2016/7/13.
 */
public class SerialUtil {
    public static String getCPUSerial() {
        String serial = "";
        try {
            long start = System.currentTimeMillis();
            Process process = Runtime.getRuntime().exec(new String[]{"wmic", "cpu", "get", "ProcessorId"});
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String property = sc.next();
            serial = sc.next();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serial;
    }

    public static void main(String[] args) {
        String userdir = System.getProperty("user.home");
        System.out.println(userdir);
    }
}
