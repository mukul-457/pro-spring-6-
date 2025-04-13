package example.spring.four;


import examples.spring.four.impl.AllConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

import static org.junit.jupiter.api.Assertions.*;

class TestMessageProvider implements MessageProvider {
    private final String message;

    public TestMessageProvider(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
@Configuration
class TestConfig{
    @Bean
    @Profile("test")
    TestMessageProvider messageProvider(){
        return new TestMessageProvider("test message");
    }
}


/*
since test profile has been activated , the messageProvider defined in AllConfig
will not be used , instead the messageProvider defined in TestConfig will be used
as it is marked with @Profile(Test)
 */
@ActiveProfiles("test")
@SpringJUnitConfig(classes = {AllConfig.class, TestConfig.class})
public class TestProfileDemo {
    @Autowired
    MessageProvider messageProvider;

    @Autowired
    MessageRenderer messageRenderer;
    @Test
    void testRenderer(){

        assertAll("messageTest",
                () -> assertNotNull(messageProvider),
                () -> assertNotNull(messageRenderer),
                () -> assertTrue(messageProvider instanceof  TestMessageProvider),
                () -> assertEquals(messageProvider, messageRenderer.getMessageProvider())
        );
        messageRenderer.render();
    }
}
