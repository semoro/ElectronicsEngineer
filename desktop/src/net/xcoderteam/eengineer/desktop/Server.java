package net.xcoderteam.eengineer.desktop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fantasyday on 11.05.2015.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        while(true){
            Socket s = ss.accept();

        }
    }
}
