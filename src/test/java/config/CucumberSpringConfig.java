package config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {TestingContext.class})
public class CucumberSpringConfig extends TestingContext {
}
