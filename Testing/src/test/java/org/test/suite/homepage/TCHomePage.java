package org.test.suite.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.test.page.PageHome;
import org.test.page.PageLogin;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.testng.annotations.*;

public class TCHomePage {

    ChromeDriver driver;
    PageLogin pagelogin;
    PageHome pageHome;
    WebDriverWait wait;

    By h1 = By.xpath("//div[@class='por d-flex align-items-center']");
    By btnEdit = By.xpath("//img[@class='bg-image-model img30-30 bd-radius-50p mw30']");
    By btnInfor = By.xpath("//a[@translate='editData']");

    @DataProvider(name = "data_TC01_ChangeInfor")
    public Object[][] createData1() {
        return new Object[][]{
                {"Nguyen Van A", "Thành công"},
        };
    }

    @BeforeMethod
    public void FOA() {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://globedr.com/signin");

        pagelogin = new PageLogin(driver);
        pageHome = new PageHome(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Initialize wait here

        pagelogin.login("0359768661", "0359768661");
        wait.until(ExpectedConditions.visibilityOfElementLocated(h1));

        driver.findElement(btnEdit).click();
        driver.findElement(btnInfor).click();
    }

    @AfterMethod
    public void AOA() {
        driver.quit();
    }

    @Test(dataProvider = "data_TC01_ChangeInfor")
    public void TC01_LoginValid(String yourname, String expected) {
        driver.findElement(By.xpath("//label[@translate='yourName']")).sendKeys(yourname);
        driver.findElement(By.xpath("//input[@placeholder='Ngày/tháng/năm']")).sendKeys("28112003");
        driver.findElement(By.xpath("//label[@translate='gender']")).click();
        driver.findElement(By.xpath("//option[contains(text(),'Nam')]")).click();
        driver.findElement(By.xpath("//a[@translate='save']")).click();

        Assert.assertEquals(pageHome.getAlertDialog(), expected);
    }
}
