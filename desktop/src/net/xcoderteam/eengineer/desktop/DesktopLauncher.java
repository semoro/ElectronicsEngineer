package net.xcoderteam.eengineer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.xcodersteam.eengineer.EEngineerGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Date;

public class DesktopLauncher {

	public static void main (String[] arg) {
        System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 600;
        config.width = 600;
        config.resizable = false;
        new LwjglApplication(new EEngineerGame(), config);
        Thread.setDefaultUncaughtExceptionHandler((t1, e) -> {
            try {
                PrintWriter pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream("crash_"+new Date()+".log")));
                pw.print("Exception in thread \""+t1.getName()+"\": ");
                e.printStackTrace(pw);
                pw.flush();
                pw.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                JFrame frame = new JFrame();
                frame.setLayout(new BorderLayout());
                Image i=ImageIO.read(DesktopLauncher.class.getResource("/crash.jpg"));
                int h=400;
                int w=i.getWidth(null)*h /i.getHeight(null);

                ImageIcon ii=new ImageIcon( i.getScaledInstance(w,h,Image.SCALE_DEFAULT));
                JLabel background = new JLabel(ii);
               // frame.setContentPane(background);
                frame.add(background,BorderLayout.CENTER);
                JLabel msg=new JLabel("Все сломалось.. ;(  Мы починим. Когда-нибудь....");
                frame.add(msg,BorderLayout.SOUTH);
                frame.pack();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.show();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

	}
}
