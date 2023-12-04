package pageObjects;

import browsers.ChromeBrowser;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class RegistrationPage {

    @Inject private ChromeBrowser chromebrowser;

    //Finding elements by xpath
    public WebElement labelEmailError() {
        return chromebrowser.getDriver().findElement(By.xpath("//p[text()='You must provide a valid email address']"));
    }

    public WebElement labelPasswordError() {
        return chromebrowser.getDriver().findElement(By.xpath("//p[text()='Your password must be longer than 8 characters']"));
    }

    public WebElement labelConfirmError() {
        return chromebrowser.getDriver().findElement(By.xpath("//p[text()='Your passwords did not match']"));
    }

    public WebElement inputName() {
        chromebrowser.getDriver().findElement(By.xpath("//input[@id='name']")).clear();
        return chromebrowser.getDriver().findElement(By.xpath("//input[@id='name']"));
    }

    public WebElement inputEmail() {
        chromebrowser.getDriver().findElement(By.xpath("//input[@id='email']")).clear();
        return chromebrowser.getDriver().findElement(By.xpath("//input[@id='email']"));
    }

    public WebElement inputWeb() {
        chromebrowser.getDriver().findElement(By.xpath("//input[@id='url']")).clear();
        return chromebrowser.getDriver().findElement(By.xpath("//input[@id='url']"));
    }

    public WebElement inputInterests() {
        chromebrowser.getDriver().switchTo().frame("iframe");
        chromebrowser.getDriver().findElement(By.xpath("//textarea[@id='jobInterests']")).clear();
        return chromebrowser.getDriver().findElement(By.xpath("//textarea[@id='jobInterests']"));
    }

    public WebElement inputPassword() {
        chromebrowser.getDriver().switchTo().parentFrame();
        chromebrowser.getDriver().findElement(By.xpath("//input[@id='password']")).clear();
        return chromebrowser.getDriver().findElement(By.xpath("//input[@id='password']"));
    }

    public WebElement inputConfirm() {
        chromebrowser.getDriver().findElement(By.xpath("//input[@id='confirmPassword']")).clear();
        return chromebrowser.getDriver().findElement(By.xpath("//input[@id='confirmPassword']"));
    }

    public WebElement btnRegister() {
        return chromebrowser.getDriver().findElement(By.xpath("//button[text()='Register']"));
    }

    public void RegisterAndNavigateToOpenAthensDashboardPage(String firstname, String email, String web, String interests, String password, String confirm)
    {
        //Navigate to the OpenAthens Dashboard webpage in the browser
        inputName().sendKeys(firstname);
        inputEmail().sendKeys(email);
        inputWeb().sendKeys(web);
        inputInterests().sendKeys(interests);
        inputPassword().sendKeys(password);
        inputConfirm().sendKeys(confirm);
        btnRegister().click();
    }

    public void assertConfirmValidationError(String errorMsg)
    {
        Assert.assertTrue(labelConfirmError().getText().equals(errorMsg));
    }

    public void assertEmailValidationError(String errorMsg)
    {
        Assert.assertTrue(labelEmailError().getText().equals(errorMsg));
    }

    public void assertPasswordValidationError(String errorMsg)
    {
        Assert.assertTrue(labelPasswordError().getText().equals(errorMsg));
    }
}