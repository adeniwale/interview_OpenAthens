package config;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"beans", "browsers", "pageObjects"})
public class TestingContext {
}
