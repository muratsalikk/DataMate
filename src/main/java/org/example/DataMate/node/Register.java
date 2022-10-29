package org.example.DataMate.node;

import org.example.DataMate.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Register {
    Logger log = LoggerFactory.getLogger(this.getClass());
    AppProperties cp;
    int port;
    int nodeCommunicationPort;
    String hash;
    String coordinatorHost;

    public Register() {
        cp = new AppProperties();
        port = cp.getCoordinatorPort();
        hash = cp.getCoordinatorHash();
        coordinatorHost = cp.getCoordinatorHost();
        nodeCommunicationPort = cp.getNodeCommunicationPort();
    }

    public void register() {
        try{
            Socket s=new Socket(coordinatorHost,port);
            log.debug("Connecting to Socket on: " + coordinatorHost +':'+port);
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            dout.writeUTF(cp.getCoordinatorHash()+"@"+nodeCommunicationPort +"@"+InetAddress.getLocalHost().getHostName());
            dout.flush();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            if (dis.readUTF().equals("updateYourStatus")) {
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                NodeStatus ns = new NodeStatusFactory().createNodeStatus();
                oos.writeObject(ns);
                oos.flush();
                log.debug("Connection Successful. Sending: " + ns.toString());
                oos.close();
            }
            dis.close();
            dout.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
