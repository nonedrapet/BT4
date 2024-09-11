package org.test;

import  org.openqa.selenium.chrome.ChromeOptions;
import  org.openqa.selenium.chrome.ChromeDriver;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.devtools.v85.page.Page;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.test.page.PageHome;

import org.test.page.PageLogin;

public class Main {
    public static void main (String[] args)
    {
        ChromeOptions chromeOptions= new ChromeOptions();
        //chromeOptions.enableBiDi();

        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get("https://globedr.com/signin");

        PageLogin pagelogin = new PageLogin(driver);
        pagelogin.login("0359768661","0359768661");

        PageHome pageHome = new PageHome(driver);
        String name = pageHome.getAccount();

        System.out.println("Name: " + name);
//        driver.quit();
    }
}
