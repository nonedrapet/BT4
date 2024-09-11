package org.test.suite.login;

//import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.test.page.PageHome;
import org.test.page.PageLogin;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.xml.crypto.Data;

public class TCLogin {

    ChromeDriver driver;
    PageLogin pagelogin;


    @DataProvider(name = "data_TC02_LoginInValid")
    public Object[][] createData1() {
        return new Object[][]{
                { "0359768661", "123456","Vui lòng kiểm tra lại số điện thoại, email hoặc mật khẩu của bạn" },
                { "0123456789", "0359768661","Vui lòng kiểm tra lại số điện thoại, email hoặc mật khẩu của bạn" },
        };
    }
    @DataProvider(name = "data_TC01_LoginValid")
    public Object[][] createData2() {
        return new Object[][]{
                { "0359768661", "0359768661","Vo Huu Sang"},
        };
    }
    @BeforeMethod
    public void FOA()
    {
        ChromeOptions chromeOptions= new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://globedr.com/signin");
        pagelogin = new PageLogin(driver);
    }

    @AfterTest
    public void AOA(){
        driver.quit();
    }


    @Test(dataProvider ="data_TC01_LoginValid")
    public void TC01_LoginValid(String dtuser,String dtpassw,String exected) {

        pagelogin.login(dtuser,dtpassw);

        PageHome pageHome = new PageHome(driver);
        String name = pageHome.getAccount();

        Assert.assertEquals(name,exected);

        System.out.println("Name: " + name);
        driver.close();
    }
    @Test(dataProvider = "data_TC02_LoginInValid")
    public void TC02_LoginInValid(String dtuser,String dtpassw,String exected) {

        pagelogin.login(dtuser,dtpassw);
        Assert.assertEquals(pagelogin.getAlertWarning(),exected);
        driver.close();
    }
}
