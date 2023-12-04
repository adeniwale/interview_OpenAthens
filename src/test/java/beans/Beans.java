package beans;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Beans {
    @Bean
    public ChromeOptions ChromeOptionsBean() {
        return new ChromeOptions();
    }
}
