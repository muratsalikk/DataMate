package org.example.DataMate.node;

import org.example.DataMate.coordinator.CoordinatorMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NodeMain {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(NodeMain.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}
