package org.example.DataMate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
    int coordinatorPort;
    String coordinatorHash;
    String coordinatorHost;
    int nodeCommunicationPort;

    public AppProperties() {
        getProperties();
    }
    public int getCoordinatorPort() {
        return coordinatorPort;
    }
    public String getCoordinatorHash() {
        return coordinatorHash;
    }
    public String getCoordinatorHost() {
        return coordinatorHost;
    }
    public int getNodeCommunicationPort() {
        return nodeCommunicationPort;
    }

    private void getProperties() {
        try (InputStream input = new FileInputStream("src/main/resources/coordinator.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            coordinatorPort = Integer.parseInt(prop.getProperty("coordinator.port"));
            coordinatorHash = prop.getProperty("coordinator.hash");
            coordinatorHost = prop.getProperty("coordinator.host");
            nodeCommunicationPort = Integer.parseInt(prop.getProperty("node.communication.port"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e){
            throw new RuntimeException(e);
        }
    }
}
