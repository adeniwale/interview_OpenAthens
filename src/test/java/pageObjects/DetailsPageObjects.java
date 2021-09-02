package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetailsPageObjects {

    WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //Finding elements by xpath
    public WebElement buttonCheckAnotherVehicle() {
        return driver.findElement(By.xpath("//a[@class='jsx-503364468 ' and contains(text(), 'Check Another Vehicle')]"));
    }

    public WebElement cellIdentityValue(String identity) {
        return driver.findElement(By.xpath("//dt[contains(text(),'" + identity + "')]/following-sibling::dd"));
    }
}