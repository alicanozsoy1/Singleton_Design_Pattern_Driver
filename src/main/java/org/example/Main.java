package org.example;

import org.example.Driver.Singleton;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Singleton s = Singleton.getDriverInstance();
        s.getDriver("chrome"); //PARAMETERIZED
        //s.getDriver(); //WITHOUT PARAMETER
        Thread.sleep(5000);
        s.closeDriver();

    }
}