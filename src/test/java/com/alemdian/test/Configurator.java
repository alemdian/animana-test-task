package com.alemdian.test;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurator {

    public static final Properties PROPERTIES = readPropertiesFromFile();
    public static final String USERNAME = PROPERTIES.getProperty("browser.stack.username");
    public static final String AUTOMATE_KEY = PROPERTIES.getProperty("browser.stack.key");
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void setupProject() {
        if (isRemote()) {
            Configuration.remote = URL;
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browser", getBrowserName());
            caps.setCapability("browser_version", "69.0");
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "10");
            caps.setCapability("resolution", "1024x768");
            caps.setCapability("name", "Bstack-[Java] Sample Test");
        } else {
            Configuration.browser = getBrowserName();
            Configuration.startMaximized = Boolean.parseBoolean(PROPERTIES.getProperty(("browser.maximized")));
        }

    }

    private static String getBrowserName() {
        if (System.getProperty("selenide.browser") == null) {
            return PROPERTIES.getProperty(("browser.name"));
        } else {
            return System.getProperty("selenide.browser");
        }
    }

    private static Properties readPropertiesFromFile() {
        Properties prop = new Properties();
        InputStream inStream = (Configurator.class.getClassLoader().getResourceAsStream("project.properties"));
        try {
            prop.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    static private Boolean isRemote() {
        return Boolean.parseBoolean(PROPERTIES.getProperty("browser.headless"));
    }

}
