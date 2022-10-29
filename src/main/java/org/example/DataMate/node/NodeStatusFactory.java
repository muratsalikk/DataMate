package org.example.DataMate.node;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NodeStatusFactory {
    private Runtime runtime;
    String osInfo;
    long usedMem;
    long totalMem;
    long freeMem;
    int availableProcessors;
    long timeStamp;
    String stringTimeStamp;

    public NodeStatus createNodeStatus() {
        NodeStatus ns = new NodeStatus();
        runtime = Runtime.getRuntime();
        osInfo = System.getProperty("os.name") +"\t"+ System.getProperty("os.version");
        totalMem = runtime.totalMemory();
        freeMem = runtime.freeMemory();
        usedMem = totalMem - freeMem;
        availableProcessors = runtime.availableProcessors();
        timeStamp = System.currentTimeMillis();
        stringTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeStamp));
        ns.setOsInfo(osInfo);
        ns.setTotalMem(totalMem);
        ns.setFreeMem(freeMem);
        ns.setUsedMem(usedMem);
        ns.setAvailableProcessors(availableProcessors);
        ns.setTimeStamp(timeStamp);
        return ns;
    }
}
