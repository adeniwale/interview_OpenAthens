package hooks;

import drivers.ChromeDriver;
import pageObjects.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class FreeCarCheckHooks {

    ChromeDriver chromedriver;

    public FreeCarCheckHooks(ChromeDriver chromedriver) {
        this.chromedriver = chromedriver;
    }

    @Before(value = "@FrontendTest", order = 10)
    public void beforeTestRun() {
        chromedriver.setup();
    }

    @Before(value = "@FrontendTest", order = 20)
    public void beforeScenario() {
        HomePageObjects fccHomePO = new HomePageObjects();
        fccHomePO.setDriver(ChromeDriver.getDriver());
        fccHomePO.EnsureFreeCarChecksPageIsOpened();
    }

    @After("@FrontendTest")
    public void afterScenario() {
        chromedriver.teardown();
    }
}
