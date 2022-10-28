package org.example;

import org.example.DataMate.AppProperties;

public class Main {
    public static void main(String[] args) {
        AppProperties cp = new AppProperties();
        System.out.println(cp.getCoordinatorHash());
    }
}