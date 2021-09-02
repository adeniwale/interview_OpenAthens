package hooks;

import drivers.ChromeDriver;
import pageObjects.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import org.openqa.selenium.chrome.ChromeOptions;

public class FreeCarCheckHooks {

    private final ChromeOptions options;
    private final ChromeDriver chromedriver;
    private final HomePageObjects fccHomePO;

    public FreeCarCheckHooks(ChromeOptions options, ChromeDriver chromedriver, HomePageObjects fccHomePO) {
        this.options = options;
        this.chromedriver = chromedriver;
        this.fccHomePO = fccHomePO;
    }

    @Before(value = "@FrontendTest", order = 10)
    public void beforeTestRun() {
        chromedriver.setup(options);
    }

    @Before(value = "@FrontendTest", order = 20)
    public void beforeScenario() {
        fccHomePO.setDriver(ChromeDriver.getDriver());
        fccHomePO.EnsureFreeCarCheckPageIsOpened();
    }

    @After("@FrontendTest")
    public void afterScenario() {
        chromedriver.teardown();
    }
}
