package pageObjects;

import org.openqa.selenium.*;

public class HomePageObjects {

    String freeCarChecksURL = "https://cartaxcheck.co.uk/";

    WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //Finding elements by xpath
    public WebElement eltVehicleNotFound() {
        return driver.findElement(By.xpath("//a[@class='jsx-2611738455 ' and text()='Try Again']"));
    }

    public WebElement inputEnterRegistration() {
        return driver.findElement(By.xpath("//input[@placeholder='Enter Registration']"));
    }

    public WebElement buttonFreeCarCheck() {
        return driver.findElement(By.xpath("//button[text()='Free Car Check']"));
    }

    public void EnsureFreeCarChecksPageIsOpened()
    {
        //Open the free car checks page in the browser if not opened yet
        if (!driver.getCurrentUrl().equals(freeCarChecksURL)) {
            driver.navigate().to(freeCarChecksURL);
        }
    }
}
