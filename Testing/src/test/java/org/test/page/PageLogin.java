package org.test.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageLogin extends BaseData {


    By txtUserName = By.xpath("//input[@id='UserName']");
    By txtPassword = By.xpath("//input[@id='Password']");
    By btnSignIn = By.xpath("//button[@translate='signIn']");
    By CountryCode = By.xpath("//button[@dropdowntoggle]");
    By alertWarning = By.xpath("//div[@role='alertdialog']");

    public PageLogin (WebDriver driver)
    {
        super(driver);
        //wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void login(String emailOrPhone, String password)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtUserName));
       // driver.findElement(txtUserName).clear();
        driver.findElement(txtUserName).sendKeys(emailOrPhone);

        wait.until(d -> {
            WebElement e = driver.findElement(CountryCode);
            if (e != null)
            {
                String txt = e.getText();
                if (txt != null && !txt.isEmpty() && !txt.equalsIgnoreCase("+")) return true;
            }
            return false;
        } );
        //driver.findElement(txtPassword).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword));
        driver.findElement(txtPassword).sendKeys(password);
        driver.findElement(btnSignIn).click();

        //return new PageHome(driver);
    }

    public String getAlertWarning()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertWarning)).getText();
    }

}
