package pageObjects;

import browsers.ChromeBrowser;
import org.openqa.selenium.*;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.nio.file.Paths;

@Component
public class HomePage {

    @Inject private ChromeBrowser chromebrowser;

    private String oaOpenAthensURL;

    public HomePage() {
        this.oaOpenAthensURL = "file:///" + Paths.get("registration-test/index.html").toAbsolutePath().toString().replaceAll("\\\\", "/");
    }

    //Finding elements by xpath
    public WebElement btnRegister() {
        return chromebrowser.getDriver().findElement(By.xpath("//a[text()='Register']"));
    }

    public void EnsureOpenAthensHomePageIsOpened()
    {
        //Open the OpenAthens home page in the browser if not opened yet
        if (!chromebrowser.getDriver().getCurrentUrl().equals(oaOpenAthensURL)) {
            chromebrowser.getDriver().navigate().to(oaOpenAthensURL);
        }
    }

    public void NavigateToOpenAthensRegistrationPage()
    {
        //Navigate to the OpenAthens Registration webpage in the browser
        EnsureOpenAthensHomePageIsOpened();
        btnRegister().click();
    }
}