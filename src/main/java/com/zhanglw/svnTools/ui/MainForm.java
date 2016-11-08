package com.zhanglw.svnTools.ui;

import com.zhanglw.svnTools.log.JTextAreaAppender;
import com.zhanglw.svnTools.service.SVNService;
import com.zhanglw.svnTools.service.impl.SVNServiceImpl;
import com.zhanglw.svnTools.util.ConfUtil;
import com.zhanglw.svnTools.util.MessageBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by zhanglw on 2016/7/9.
 */
public class MainForm extends JFrame {
    private static Logger logger = LogManager.getLogger( "My Logger" );


    private JButton pullBtn;
    private JTextField testField;
    private JTextField trunkField;
    private JTextField remarkField;
    private JButton clsbutton;
    private JLabel testLabel;
    private JLabel trunkLabel;
    private JLabel remarkLabel;
    private JPanel mainPanel;
    private JTextArea textArea;
    private SVNService svnService;

    public MainForm() {

        super( "Pull Trunk Tools" );

        svnService = new SVNServiceImpl();
        initForm();
        initBtnAction();
        initTestFieldAction();
        initTrunkFieldAction();

        logger.info( "初始化完成" );

        check();


    }

    public static void main(String[] args) {
        MainForm form = new MainForm();
    }


    private void check() {
        if (!ConfUtil.check()) {
            LoginDialog dialog = new LoginDialog( this, true );
            dialog.setVisible( true );
        }else{
            logger.info( "已登录" );
        }
    }

    private void initForm() {
        logger.info( "初始化窗体" );
        mainPanel = new JPanel();
        testLabel = new JLabel();
        testField = new JTextField( "", 50 );
        trunkLabel = new JLabel();
        trunkField = new JTextField( "", 50 );
        remarkLabel = new JLabel();
        remarkField = new JTextField( "", 50 );
        textArea = new JTextArea( 10, 60 );
        pullBtn = new JButton();
        clsbutton = new JButton();

        mainPanel.setLayout( new GridBagLayout() );
        mainPanel.setBackground( Color.GRAY );
        mainPanel.setBorder( BorderFactory.createEmptyBorder( 50, 50, 50, 50 ) );
        mainPanel.setBounds( 0, 0, 100, 100 );
        mainPanel.setOpaque( true );

        testLabel.setText( "Test SVN URL" );
        testLabel.setSize( 40, 30 );
        testField.setText( "Test SVN URL" );

        trunkLabel.setText( "Trunk SVN URL" );
        trunkField.setText( "Trunk SVN URL" );
        trunkField.setEnabled( false );

        remarkLabel.setText( "备注" );
        remarkField.setText( "" );
        remarkField.setEnabled( true );
        remarkField.setToolTipText( "双击删除全部字符" );

        pullBtn.setText( "Pull Trunk" );

        clsbutton.setText( "clear log" );

        // logText

        textArea.setLineWrap( true );
        textArea.setWrapStyleWord( true );
        textArea.setEditable( true );
        textArea.setFont( new Font( "Courier", Font.PLAIN, 12 ) );
        // Make scrollable console pane
        JScrollPane jConsoleScroll = new JScrollPane( textArea );
        jConsoleScroll.setAutoscrolls( true );
        jConsoleScroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        // Subscribe the text area to JTextAreaAppender
        JTextAreaAppender.addTextArea( textArea );


        // init layout
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets( 10, 10, 10, 10 );
        mainPanel.add( testLabel, gbc );

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add( testField, gbc );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets( 10, 10, 10, 10 );
        mainPanel.add( trunkLabel, gbc );

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add( trunkField, gbc );

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets( 10, 10, 10, 10 );
        mainPanel.add( remarkLabel, gbc );

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add( remarkField, gbc );

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainPanel.add( pullBtn, gbc );


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTH;
        mainPanel.add( jConsoleScroll, gbc );

        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainPanel.add( clsbutton, gbc );

        this.getContentPane().add( mainPanel );
        this.setResizable( false );
        this.pack();
        this.setVisible( true );
        this.setLocationRelativeTo( null );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    private void initBtnAction() {
        pullBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clickAction();
            }
        } );
        clsbutton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clsbuttonClickAction();
            }
        } );
    }

    private void clsbuttonClickAction() {
        this.textArea.setText( "" );
    }


    private void clickAction() {

        String testUrl = this.testField.getText();
        String trunkUrl = this.trunkField.getText();

        if (testUrl == null || testUrl.length() == 0) {
            MessageBox.Show( "Test SVN地址不能为空" );
            logger.info( "TestSVN为空" );
            return;
        }

        if (!testUrl.contains( ConfUtil.getConfigBean( "config.yaml" ).getTestSVNUrl().substring( ConfUtil.getConfigBean( "config.yaml" ).getTestSVNUrl().indexOf( "http://" ) ) )) {
            MessageBox.Show( "SVN的地址不合法" );
            logger.info( "SVN的地址不合法" );
            return;
        }

        if (!svnService.isExist( testUrl )) {
            MessageBox.Show( "Test SVN 地址不存在!" );
            logger.info( "Test SVN 地址不存在!" );
            return;
        }
        if (svnService.isExist( trunkUrl )) {
            int result = MessageBox.Show( "Trunk SVN 地址已经存在,是否覆盖!", MessageBox.YES_NO_CANCEL_OPTION );
            if (result == MessageBox.YES_OPTION) {
                svnService.delDir( trunkUrl, this.remarkField.getText() );
            } else {
                return;
            }
        }

        String message = svnService.copyToTrunk( testUrl, trunkUrl, remarkField.getText() );
        MessageBox.Show( message + "\r\n" + "拉取成功" );
        logger.info( "拉取成功:\r\n" + trunkUrl + "\r\n备注:" + remarkField.getText() );
    }

    private void initTestFieldAction() {
        testField.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked( e );
                testField.setText( "" );
            }
        } );
        remarkField.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked( e );
                if (e.getClickCount() == 2) {
                    remarkField.setText( "*" );
                }
            }
        } );
        testField.getDocument().addDocumentListener( new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                testFieldAction();
            }

            public void removeUpdate(DocumentEvent e) {
                testFieldAction();
            }

            public void changedUpdate(DocumentEvent e) {
            }
        } );
    }

    private void testFieldAction() {
        if (testField.getText() != null && !testField.getText().equals( "" )) {
            this.trunkField.setText( testField.getText().replace( "/test/", "/trunk/" ) );
        }
    }

    private void initTrunkFieldAction() {
        trunkField.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked( e );
                trunkFieldAction( e );
            }
        } );
    }

    private void trunkFieldAction(MouseEvent e) {
        if (e.getClickCount() == 2) {
            this.trunkField.setEnabled( !this.trunkField.isEnabled() );
        }
    }
}
