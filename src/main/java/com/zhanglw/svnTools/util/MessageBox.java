package com.zhanglw.svnTools.util;

import javax.swing.*;
/**
 * Created by zhanglw on 2016/7/12.
 */


public class MessageBox {
    private static String Title = "提示";
    public static final int DEFAULT_OPTION = -1;
    public static final int YES_NO_OPTION = 10;
    public static final int YES_NO_CANCEL_OPTION = 11;
    public static final int OK_CANCEL_OPTION = 12;
    public static final int YES_OPTION = 0;
    public static final int NO_OPTION = 1;
    public static final int CANCEL_OPTION = 2;
    public static final int OK_OPTION = 0;
    public static final int CLOSED_OPTION = -1;
    public static final int ERROR_MESSAGE = 0;
    public static final int INFORMATION_MESSAGE = 1;
    public static final int WARNING_MESSAGE = 2;
    public static final int QUESTION_MESSAGE = 3;
    public static final int PLAIN_MESSAGE = -1;

    public static int Show(String Msg, int Option) {
        switch (Option) {
            case YES_NO_OPTION:
            case YES_NO_CANCEL_OPTION:
            case OK_CANCEL_OPTION:
                return JOptionPane.showConfirmDialog(null, Msg, Title, Option - 10);
            default:
                JOptionPane.showMessageDialog(null, Msg, Title, Option);
        }
        return 0;
    }

    public static int Show(String Msg) {
        return Show(Msg, INFORMATION_MESSAGE);
    }
}

