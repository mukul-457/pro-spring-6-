package spring.examples.six.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "spring.examples.six.jdbc.templateDao")
public class TemplateDaoConfig {
}
