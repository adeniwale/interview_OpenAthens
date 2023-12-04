package hooks;

import browsers.ChromeBrowser;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import javax.inject.Inject;

public class OpenAthensHooks {

    @Inject private ChromeBrowser chromebrowser;
    @Inject private ChromeOptions options;
    @Inject private HomePage oaHomePageObj;

    @Before(value = "@FrontendTest", order = 10)
    public void initBrowser() {
        chromebrowser.setup(options);
    }

    @Before(value = "@FrontendTest", order = 15)
    public void beforeScenario() {
        oaHomePageObj.EnsureOpenAthensHomePageIsOpened();
    }

    @After("@FrontendTest")
    public void quitBrowser() {
        chromebrowser.teardown();
    }
}
