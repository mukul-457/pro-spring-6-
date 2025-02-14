package spring.examples.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Chapter4BootApplication {
    private static Logger logger = LoggerFactory.getLogger(Chapter4BootApplication.class);
    public static void main(String[] args) {
        var ctx = SpringApplication.run(Chapter4BootApplication.class, args);
        assert (ctx != null);
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(logger::info);
    }

}
