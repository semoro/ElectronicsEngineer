package net.xcoderteam.eengineer.desktop;

import net.xcodersteam.eengineer.packets.*;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by fantasyday on 11.05.2015.
 */
public class ClientHandler implements Runnable{
    public Socket s;
    public static final File file = new File("./tasks");
    public ClientHandler(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            while(s.isConnected()){
                if(ois.available() > 0){
                    Object o = ois.readObject();
                    if(o instanceof ListDirectory){
                        File tmp = new File(file, ((ListDirectory) o).directory.fullPatch);
                        TaskDirectory dir = ((ListDirectory) o).directory;
                        ArrayList<TaskRec> content = new ArrayList<>();
                        for(File f : tmp.listFiles()){
                            if(f.isDirectory()){
                                TaskDirectory td = new TaskDirectory();
                                td.name = f.getName();
                                td.fullPatch = f.getPath();
                                content.add(td);
                            }else if(f.getName().endsWith(".task")){
                                TaskDef td = new TaskDef();
                                td.name = f.getName();
                                td.fullPatch = f.getPath();
                                content.add(td);
                            }
                        }
                        dir.content = content.toArray(new TaskRec[0]);
                        oos.writeObject(dir);
                    }else if(o instanceof GetTask){

                    }
                }else{
                    Thread.sleep(100);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
