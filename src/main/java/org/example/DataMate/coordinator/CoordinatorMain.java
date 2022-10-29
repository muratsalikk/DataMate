package org.example.DataMate.coordinator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CoordinatorMain {
    static Logger log = LoggerFactory.getLogger(CoordinatorMain.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CoordinatorMain.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}
