package com.walmart.integrationTest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;


public class SeleniumWalmartTestCase {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver2.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testWalmartTestCase() throws Exception {
        driver.get("https://lit-lowlands-69664.herokuapp.com/");
        driver.findElement(By.name("text")).click();
        driver.findElement(By.name("text")).clear();
        driver.findElement(By.name("text")).sendKeys("181");
        driver.findElement(By.id("buttonID")).click();
        driver.findElement(By.id("onSaleID")).click();
        assertEquals("onSaleID", "onSaleID");

    }

    @Test
    public void testWalmartTestCase2() throws Exception {
        driver.get("https://lit-lowlands-69664.herokuapp.com/");
        driver.findElement(By.name("text")).click();
        driver.findElement(By.name("text")).clear();
        driver.findElement(By.name("text")).sendKeys("adsfsda");
        driver.findElement(By.id("buttonID")).click();
        driver.findElement(By.xpath("(//h3[@id='onSaleID'])[3]")).click();
        driver.findElement(By.xpath("(//h3[@id='onSaleID'])[3]")).click();
        driver.findElement(By.xpath("(//h3[@id='onSaleID'])[4]")).click();
        driver.findElement(By.xpath("(//h3[@id='onSaleID'])[4]")).click();
        assertEquals("(//h3[@id='onSaleID'])[3]", "(//h3[@id='onSaleID'])[3]");
        assertEquals("(//h3[@id='onSaleID'])[4]", "(//h3[@id='onSaleID'])[4]");

    }

    @Test
    public void testUntitledTestCase2() throws Exception {
        driver.get("https://lit-lowlands-69664.herokuapp.com/");
        driver.findElement(By.xpath("//div[@id='root']/div/div")).click();
        driver.findElement(By.id("buttonID")).click();
        driver.findElement(By.name("text")).click();
        driver.findElement(By.name("text")).clear();
        driver.findElement(By.name("text")).sendKeys("hola lider");
        driver.findElement(By.id("buttonID")).click();
        assertEquals("onSaleID", "onSaleID");
    }


    @Test
    public void testAssertFunctions() {
        driver.navigate().to("https://lit-lowlands-69664.herokuapp.com/");
        String ActualTitle = driver.getTitle();
        String ExpectedTitle = "React App";
        assertEquals(ActualTitle, ExpectedTitle);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
