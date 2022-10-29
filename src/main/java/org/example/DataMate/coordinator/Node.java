package org.example.DataMate.coordinator;
import org.example.DataMate.node.NodeStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Node extends Thread {
    Logger log = LoggerFactory.getLogger(this.getClass());
    NodeStatus status;
    String hostname;
    int port;
    boolean nodeAvailability;

    public Node(NodeStatus status,String hostname, int port) {
        this.status = status;
        this.hostname = hostname;
        this.port = port;
        log.trace("Node initialised: " + "status: " + this.status + " hostname: " + this.hostname + " port: " + this.port);
    }

    private NodeStatus updateStatus(Socket s) {
        NodeStatus ns;
        DataOutputStream dos;
        ObjectInputStream ois;;
        try {
            dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("updateYourStatus");
            dos.flush();
            log.trace("update request sent.");
            ois = new ObjectInputStream(s.getInputStream());
            ns = (NodeStatus) ois.readObject();
            log.info("Updated node status: " + ns.toString());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ns;
    }

    @Override
    public void run() {
        log.debug("Trying connect to node on " + hostname+':'+port);
        try (Socket s = new Socket(hostname,port)) {
            while (!Thread.currentThread().isInterrupted()) {
                status = updateStatus(s);
                Thread.sleep(60000);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
