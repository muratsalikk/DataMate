package org.example.DataMate.node;

import org.example.DataMate.AppProperties;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Register {
    AppProperties cp;
    int port;
    String hash;
    String coordinatorHost;

    public Register() {
        cp = new AppProperties();
        port = cp.getCoordinatorPort();
        hash = cp.getCoordinatorHash();
        coordinatorHost = cp.getCoordinatorHost();
    }

    public void register() {
        try{
            Socket s=new Socket(coordinatorHost,port);
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            dout.writeUTF(cp.getCoordinatorHash()+"@"+cp.getCoordinatorPort() +"@"+InetAddress.getLocalHost().getHostName());
            dout.flush();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            if (dis.readUTF().equals("updateYourStatus")) {
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                NodeStatus ns = new NodeStatusFactory().createNodeStatus();
                oos.writeObject(ns);
                oos.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
