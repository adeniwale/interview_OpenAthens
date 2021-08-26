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
    public WebElement CheckAnotherVehicle() {
        driver.findElement(By.xpath("//span[@class='jsx-1684733318 nav-toggle']")).click();
        return driver.findElement(By.xpath("//a[@class='jsx-3809232611' and text()='Free Car Check']"));
    }

    public WebElement identityValue(String identity) {
        return driver.findElement(By.xpath("//dt[contains(text(),'" + identity + "')]/following-sibling::dd"));
    }
}
