package org.example.DataMate.coordinator;

import org.example.DataMate.AppProperties;
import org.example.DataMate.node.NodeStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NodeListener extends Thread {
    Logger log = LoggerFactory.getLogger(this.getClass());

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
            log.info("Message received: " + str);
            if(strArray.length != 3) {
                log.info("Cannot parse node registiration request: " + str);
            } else if (strArray[0].equals(hash)) {
                nodePort=strArray[1];
                nodeHost=strArray[2];
                DataOutputStream dot = new DataOutputStream(s.getOutputStream());
                dot.writeUTF("updateYourStatus");
                dot.flush();
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                NodeStatus ns = (NodeStatus) ois.readObject();
                log.info("New node added:");
                log.info(ns.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
