package org.example.DataMate.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class NodeService {
    Logger log = LoggerFactory.getLogger(this.getClass());
    public NodeService() {
        log.info("NodeService started.");
        Coordinator c = new Coordinator(5020);
        c.start();
        Register r = new Register();
        r.register();

    }

}
