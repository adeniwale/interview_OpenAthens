package beans;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class Beans {
    @Bean
    public ChromeOptions ChromeOptionsBean() {
        return new ChromeOptions();
    }
}
