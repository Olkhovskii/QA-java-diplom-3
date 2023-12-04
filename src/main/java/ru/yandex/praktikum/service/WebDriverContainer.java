package ru.yandex.praktikum.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;

public class WebDriverContainer {

    public static WebDriver init() {

        WebDriver driver;
        String browserName = System.getProperty("browser");
        if (browserName==null) { browserName = ""; };

        ChromeOptions options = new ChromeOptions();
        switch (browserName) {
            case "chrome":
            case "":
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                driver = new Augmenter().augment(driver);
                break;
            case "yandex":
                driver = new ChromeDriver(options);
                driver = new Augmenter().augment(driver);
                break;
            default:throw new RuntimeException("Браузер не установлен: "+browserName);
        }

        return driver;
    }

}
