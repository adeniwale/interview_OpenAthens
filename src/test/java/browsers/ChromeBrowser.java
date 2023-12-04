package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component
public class ChromeBrowser {

    private WebDriver driver;

    public void setup(ChromeOptions options) {
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public void teardown() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
