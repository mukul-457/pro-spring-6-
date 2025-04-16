package spring.examples.chapter06boot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import spring.examples.chapter06boot.repos.SingerRepo;

@SpringBootTest(classes = Chapter06BootApplication.class)
@ActiveProfiles("testcontainer")
public class Chapter06BootApplicationTestsV2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Chapter06BootApplication.class);
    @Autowired
    SingerRepo singerRepo;
    @Test
    void testTestContainer(){
        singerRepo.findAll().forEach(s -> LOGGER.info(s.toString()));
    }
}
