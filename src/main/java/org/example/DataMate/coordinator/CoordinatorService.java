package org.example.DataMate.coordinator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CoordinatorService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    public CoordinatorService() {
        List<Long> nodeList = new ArrayList<>();
        NodeListener nl = new NodeListener(nodeList);
        nl.start();
        log.info("NodeListener started.");
        log.info(nodeList + " " + nodeList.size());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(nodeList + " " + nodeList.size());
    }
}
