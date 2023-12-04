package pageObjects;

import browsers.ChromeBrowser;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import java.util.List;

@Component
public class DashboardPage {

    @Inject private ChromeBrowser chromebrowser;

    //Finding elements by xpath
    public WebElement labelSuccess() {
        return chromebrowser.getDriver().findElement(By.xpath("//div[text()='Your registration has been successful!']"));
    }

    public WebElement labelWelcome(String firstname) {
        return chromebrowser.getDriver().findElement(By.xpath("//h2[text()='Welcome " + firstname + "!']"));
    }

    public WebElement labelJobs() {
        return chromebrowser.getDriver().findElement(By.xpath("//h3[text()='Here are our latest 5 jobs for you…']"));
    }

    public List<WebElement> tableJobs() {
        return chromebrowser.getDriver().findElements(By.xpath("//tr[@ng-repeat='job in jobs']"));
    }

    public void assertSuccessfulRegistration(String firstname)
    {
        Assert.assertTrue(labelSuccess().getText().equals("Your registration has been successful!"));
        Assert.assertTrue(labelWelcome(firstname).getText().equals("Welcome " + firstname + "!"));
        Assert.assertTrue(labelJobs().getText().equals("Here are our latest 5 jobs for you…"));
        Assert.assertTrue(tableJobs().size() == 5);
    }
}