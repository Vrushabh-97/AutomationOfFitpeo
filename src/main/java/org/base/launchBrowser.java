package org.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class launchBrowser {
    public static WebDriver driver;

    @BeforeTest
    public void Setup() throws Exception {
        System.setProperty("webdriver.chrome.driver","./latestChromeDriver/chromedriver.exe");
        driver =new ChromeDriver();
        driver.navigate().to("https://www.fitpeo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

    @AfterTest
    public void TearDown() throws Exception {
       // driver.quit();
}}
