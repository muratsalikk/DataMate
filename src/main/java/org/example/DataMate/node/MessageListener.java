package org.example.DataMate.node;

import org.example.DataMate.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageListener extends Thread {
    Logger log = LoggerFactory.getLogger(this.getClass());
    AppProperties cp;
    int port;

    public MessageListener() {
        port=cp.getNodePort();
    }

    @Override
    public void run(){

    }
}
