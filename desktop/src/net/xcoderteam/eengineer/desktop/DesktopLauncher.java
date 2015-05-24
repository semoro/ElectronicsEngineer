package net.xcoderteam.eengineer.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import net.xcodersteam.eengineer.EEngineerGame;
import net.xcodersteam.eengineer.MainGameScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DesktopLauncher {
    public static JFrame mainFrame = new JFrame();



    static class MoveMouseListener implements MouseListener, MouseMotionListener {

        Point start_drag;
        Point start_loc;

        public MoveMouseListener() {

        }


        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            this.start_drag = e.getPoint();
            this.start_loc = mainFrame.getLocation();
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
            Point current = e.getPoint();
            if (e.getY() < 35) {
                Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
                        (int) current.getY() - (int) start_drag.getY());

                Point new_location = new Point(
                        (int) (this.start_loc.getX() + offset.getX()), (int) (this.start_loc
                        .getY() + offset.getY()));
                mainFrame.setLocation(new_location);
            }
        }

        public void mouseMoved(MouseEvent e) {
        }
    }


	public static void main (String[] arg) {
        System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".task") || f.getName().endsWith(".sv") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Файл задания или сохранения";
            }
        });

        fileChooser.setDialogTitle("Задание");
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.showOpenDialog(null);
        if (fileChooser.getSelectedFile() == null)
            System.exit(0);

        LwjglApplicationConfiguration config=new LwjglApplicationConfiguration();
        config.width=600;
        config.height=600;

       new LwjglApplication(new EEngineerGame(fileChooser.getSelectedFile()),config);
      /*  mainFrame.setLayout(new BorderLayout());
        mainFrame.setUndecorated(true);
        mainFrame.setResizable(false);
        //MoveMouseListener mml=new MoveMouseListener();

        mainFrame.add(canvas.getCanvas());
       // mainFrame.addMouseListener(mml);
       // mainFrame.addMouseMotionListener(mml);
        mainFrame.pack();
        mainFrame.setTitle("Electronics Engineer");
        mainFrame.setSize(600,600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);*/

        Thread.setDefaultUncaughtExceptionHandler((t1, e) -> {
            DateFormat df = new SimpleDateFormat("MM.dd.yyyy_HHmmss");
            try {
                PrintWriter pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream("crash_"+df.format(new Date())+".log")));
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
                String saveFile;
                try{

                    saveFile="Восстановленно."+df.format(new Date())+".sv";
                    MainGameScreen.instance.task.save(new File("сохранения/"+saveFile),MainGameScreen.instance.cm);
                }catch (Exception xe){
                    xe.printStackTrace();
                    saveFile=null;
                }
                JLabel msg;
                if(saveFile==null){
                    msg=new JLabel("Все сломалось.. ;(  Мы починим. Когда-нибудь....");
                }else {
                    msg = new JLabel("Что-то пошло не так. Решение сохранено в файле " + saveFile);
                }
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
