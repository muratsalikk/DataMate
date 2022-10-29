package org.example.DataMate.coordinator;

import org.example.DataMate.AppProperties;
import org.example.DataMate.node.NodeStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class NodeListener extends Thread {
    Logger log = LoggerFactory.getLogger(this.getClass());

    AppProperties cp;
    int port;
    String hash;
    List<Long> nodeList;

    public NodeListener(List<Long> nodeList) {
        cp = new AppProperties();
        port = cp.getCoordinatorPort();
        hash = cp.getCoordinatorHash();
        this.nodeList = nodeList;
    }
    @Async
    public void nodeRegistery() {
        int nodePort;
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
                nodePort=Integer.parseInt(strArray[1]);
                nodeHost=strArray[2];
                DataOutputStream dot = new DataOutputStream(s.getOutputStream());
                dot.writeUTF("updateYourStatus");
                dot.flush();
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                NodeStatus ns = (NodeStatus) ois.readObject();
                Node node = new Node(ns, "localhost", nodePort);
                node.start();
                nodeList.add(node.getId());
                log.info("New node added: " + '\n' + nodePort + nodeHost + ns.toString());
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
                Thread.sleep(10000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

    }

}
