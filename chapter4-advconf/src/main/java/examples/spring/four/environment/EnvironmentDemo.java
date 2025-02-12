package examples.spring.four.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentDemo {
    private static Logger logger = LoggerFactory.getLogger(EnvironmentDemo.class);
    public static void main(String[] args) {
        var ctx = new GenericApplicationContext();
        ConfigurableEnvironment env = ctx.getEnvironment();
        MutablePropertySources props = env.getPropertySources();
        Map<String, Object> customProp = new HashMap<>();
        customProp.put("user.home","CUSTOM_USER_HOME");
        props.addFirst(new MapPropertySource("cust", customProp));
        logger.info("-- Env variable from Java.lang.system --");
        logger.info("SYS_USER_HOME: " + System.getProperty("user.home"));
        logger.info("SYS_JAVA_HOME: " + System.getenv("TEMP "));
        logger.info("-- Env variable from ConfigurableEnvironment");
        logger.info("USER_HOME: " + env.getProperty("user.home"));
        logger.info("JAVA_HOME: " + env.getProperty("TEMP"));

    }

}
