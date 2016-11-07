import com.zhanglw.svnTools.log.JTextAreaAppender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zhanglianwei on 2016/11/7.
 */
public class LogTest extends JFrame {


    private static Logger logger = LogManager.getLogger( "My Logger" );

    public static void main(String[] args) {

        JTextArea jLoggingConsole = new JTextArea( 50, 0 ); // 5 lines high here
        jLoggingConsole.setLineWrap( true );
        jLoggingConsole.setWrapStyleWord( true );
        jLoggingConsole.setEditable( false );
        jLoggingConsole.setFont( new Font( "Courier", Font.PLAIN, 12 ) );

        // Make scrollable console pane
        JScrollPane jConsoleScroll = new JScrollPane( jLoggingConsole );
        jConsoleScroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        // Subscribe the text area to JTextAreaAppender
        JTextAreaAppender.addTextArea( jLoggingConsole );
        LogTest logTest = new LogTest();
        logTest.setSize( 100, 500 );
        logTest.add( jLoggingConsole );
        logTest.setLocationRelativeTo( null );
        logTest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        logTest.setVisible( true );
        for (int i = 0; i < 1000; i++) {

            logger.info( "TESSSS" );
        }
    }
}
