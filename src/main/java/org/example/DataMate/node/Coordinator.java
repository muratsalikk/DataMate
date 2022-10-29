package org.example.DataMate.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Coordinator extends Thread{
    Logger log = LoggerFactory.getLogger(this.getClass());

    int nodeCommunicationPort;
    Socket socket;
    public Coordinator(int nodeCommunicationPort) {
        this.nodeCommunicationPort=nodeCommunicationPort;
    }

    public void statusUpdater() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                if(dis.readUTF().equals("updateYourStatus")) {
                    log.trace("UpdateStatus request received from coordinator.");
                    ObjectOutputStream ois = new ObjectOutputStream(socket.getOutputStream());
                    NodeStatus ns = new NodeStatusFactory().createNodeStatus();
                    ois.writeObject(ns);
                    ois.flush();
                }
                Thread.sleep(1);
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        log.debug("Trying to start socket on: " + nodeCommunicationPort);
        try(ServerSocket ss = new ServerSocket(nodeCommunicationPort)){
            socket = ss.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Thread statusUpdaterThread = new Thread(this::statusUpdater);
        statusUpdaterThread.start();
        log.info("Node registered and started to communicate with Coordinator.");

    }

}
