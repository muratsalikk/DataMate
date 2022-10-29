package org.example.DataMate.coordinator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoordinatorMain {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(CoordinatorMain.class);
        log.info("Coordinator started.");
        NodeListener nl = new NodeListener();
        log.info("NodeListener started.");
        nl.start();
        log.info("other things");
    }
}
