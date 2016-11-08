package com.zhanglw.svnTools.ui;

import com.zhanglw.svnTools.bean.AuthenBean;
import com.zhanglw.svnTools.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zhanglw on 2016/7/13.
 */
public class LoginDialog extends Dialog implements ActionListener{

    private static Logger logger = LogManager.getLogger( "My Logger" );
    JFrame parentFrame = null;
    JLabel label1 = new JLabel("用户名");
    JLabel label2 = new JLabel("密码");
    JTextField username= new JTextField(50);
    JPasswordField passwordField= new JPasswordField();
    JButton ok = new JButton("确定");
    JButton cancel = new JButton("取消");
    LoginDialog(JFrame owner, boolean modal)
    {
        super(owner, modal);
        setTitle("登录密码");
        setSize(260,140);
        setResizable(false);
        setLayout(null);
        add(label1);
        add(label2);
        label1.setBounds(50, 30, 65, 20);
        label2.setBounds(50, 60, 65, 20);
        add(username);
        add(passwordField);
        username.setBounds(120, 30, 90, 20);
        passwordField.setBounds(120, 60, 90, 20);
        add(ok);
        add(cancel);
        ok.setBounds(60, 100, 60, 25);
        cancel.setBounds(140, 100, 60, 25);
        ok.addActionListener(this);
        cancel.addActionListener(this);
        this.setLocationRelativeTo(null);
        parentFrame = owner;
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==ok)
        {
            if(!QStringUtil.isEmpty(this.username.getText()) && this.passwordField.getPassword().length>0){
                AuthenBean authenBean = ConfUtil.aesEncryptBean(this.username.getText(),String.valueOf(this.passwordField.getPassword()), SerialUtil.getCPUSerial());
                boolean falg = SVNUtil.isConnect( ConfUtil.getConfigBean( "config.yaml" ).getTestSVNUrl(),this.username.getText(),this.passwordField.getPassword());
                if(falg){
                    ConfUtil.createDat(authenBean);
                    logger.info("登录成功!");
                    dispose();
                }else {
                    MessageBox.Show("无法连接SVN: \r\n1. 用户名或密码错误 \r\n2.网络异常");
                }
            }else{
                MessageBox.Show("请输入密码!");
            }
        }else if(e.getSource()==cancel){
            this.parentFrame.dispose();
            dispose();
        }
    }
}
