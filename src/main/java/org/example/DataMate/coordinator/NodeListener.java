package org.example.DataMate.coordinator;

import org.example.DataMate.AppProperties;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NodeListener extends Thread {
    AppProperties cp;
    int port;
    String hash;

    public NodeListener() {
        cp = new AppProperties();
        port = cp.getCoordinatorPort();
        hash = cp.getCoordinatorHash();
    }

    public void nodeRegistery() {
        String nodePort;
        String nodeHost;
        try (ServerSocket ss = new ServerSocket(port)) {
            Socket s = ss.accept();
            DataInputStream dis=new DataInputStream(s.getInputStream());
            String  str=(String)dis.readUTF();
            String[] strArray = str.split("@");
            System.out.println(str);
            if(strArray.length != 3) {
                System.out.println("Cannot parse node registiration request: " + str);
            } else if (strArray[0].equals(hash)) {
                nodePort=strArray[1];
                nodeHost=strArray[2];
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        while(true) {
            nodeRegistery();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

    }

}
