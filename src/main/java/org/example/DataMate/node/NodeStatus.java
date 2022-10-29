package org.example.DataMate.node;

import java.io.Serializable;

public class NodeStatus implements Serializable {
    String osInfo;
    long usedMem;
    long totalMem;
    long freeMem;
    int availableProcessors;
    long timeStamp;
    String stringTimeStamp;

    public String getOsInfo() {
        return osInfo;
    }
    public void setOsInfo(String osInfo) {
        this.osInfo = osInfo;
    }
    public long getUsedMem() {
        return usedMem;
    }
    public void setUsedMem(long usedMem) {
        this.usedMem = usedMem;
    }
    public long getTotalMem() {
        return totalMem;
    }
    public void setTotalMem(long totalMem) {
        this.totalMem = totalMem;
    }
    public long getFreeMem() {
        return freeMem;
    }
    public void setFreeMem(long freeMem) {
        this.freeMem = freeMem;
    }
    public int getAvailableProcessors() {
        return availableProcessors;
    }
    public void setAvailableProcessors(int availableProcessors) {
        this.availableProcessors = availableProcessors;
    }
    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getStringTimeStamp() {
        return stringTimeStamp;
    }
    public void setStringTimeStamp(String stringTimeStamp){
        this.stringTimeStamp=stringTimeStamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node Status");
        sb.append('\n');
        sb.append("--OS: " + osInfo);
        sb.append('\n');
        sb.append("--Memory(mb): " + "total " + totalMem/1024 + " free " + freeMem/1024 + " used " + usedMem/1024);
        sb.append('\n');
        sb.append("--Available Processors: " + availableProcessors);
        sb.append('\n');
        sb.append("--Time: " + stringTimeStamp);
        sb.append('\n');
        return sb.toString();
    }

}
