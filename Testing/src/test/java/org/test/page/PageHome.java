package org.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageHome extends BaseData{
    By dialog = By.xpath("//div[contains(@class, 'toast-message') and text()='Thành công']");
    By waitAccount = By.xpath("//li[contains(@class, 'nav-item') and contains(@class, 'nav-account')]");
    public PageHome (WebDriver driver)
    {
        super(driver);
    }

    public String getAccount()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitAccount));
        return driver.findElement(waitAccount).getText();
    }
    public String getAlertDialog()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dialog)).getText();
    }
}
