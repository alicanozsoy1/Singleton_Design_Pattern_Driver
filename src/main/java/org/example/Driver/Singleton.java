package org.example.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;


public class Singleton {
    private static Singleton singleton = new Singleton();
    private static WebDriver driver;

    private Singleton(){
        System.out.println("Creating a singleton object");
    }

    public static Singleton getDriverInstance(){

        return singleton;
    }

    public WebDriver getDriver(String browser){

        if (driver == null)
        {
            browser = browser == null ? "chrome" : browser;

            switch (browser) {
                case "chrome" -> {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized", "--disable-notifications");
                    chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"disable-popup-blocking", "enable-automation"});
                    driver = WebDriverManager.chromedriver().avoidShutdownHook().capabilities(chromeOptions).create();
                }
                case "firefox" -> {
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                }
                case "edge" -> {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");
                    driver = new EdgeDriver(edgeOptions);
                }
                case "ie" -> {
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                }
                case "safari" -> {
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                }
                case "chrome-headless" -> {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                }
            }
        }
        return driver;
    }

    public WebDriver getDriver()
    {
        if (driver==null)
        {
            getDriver("chrome");
        }
        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
