package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriver {

    private static WebDriver driver = null;

    public void setup(ChromeOptions options) {
        options.setAcceptInsecureCerts(true);

        WebDriverManager.chromedriver().setup();
        driver = new org.openqa.selenium.chrome.ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public void teardown() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
