package spring.examples.chapter06boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;
import spring.examples.chapter06boot.repos.SingerRepo;

@SpringBootApplication
public class Chapter06BootApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(Chapter06BootApplication.class);

    public static void main(String[] args) {
        //System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
        var ctx = SpringApplication.run(Chapter06BootApplication.class, args);
        var repo = ctx.getBean(SingerRepo.class);
        repo.findAll().forEach(s -> LOGGER.info(s.toString()));
    }

}
