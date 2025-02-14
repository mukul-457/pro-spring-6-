package spring.examples.four.beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BeanTest {

    @Autowired
    ApplicationContext context;
    @Autowired
    MessageRenderer messageRenderer;
    @Autowired
    MessageProvider messageProvider;

    @Test
    void contextLoaded(){
        assertNotNull(context);
    }

    @Test
    void renderTest(){
        assertAll("messageTest",
                () -> assertNotNull(messageProvider),
                () -> assertNotNull(messageRenderer),
                () -> assertEquals(messageProvider, messageRenderer.getMessageProvider())
        );
        messageRenderer.render();

    }
}
