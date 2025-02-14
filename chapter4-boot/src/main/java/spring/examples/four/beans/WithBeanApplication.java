package spring.examples.four.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

@SpringBootApplication
public class WithBeanApplication {
    private static Logger logger = LoggerFactory.getLogger(WithBeanApplication.class);

    public static void main(String[] args) {
        var ctx = SpringApplication.run(WithBeanApplication.class, args);
        MessageRenderer mr = ctx.getBean(MessageRenderer.class);
        mr.render();
    }

}
@Component
class StandardMessageRenderer implements MessageRenderer{
    private static Logger logger = LoggerFactory.getLogger(StandardMessageRenderer.class);

    private MessageProvider messageProvider;
    @Override
    public void render() {
        logger.info(messageProvider.getMessage());
    }
    @Autowired
    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
@Component
class ConfigurableMessageProvider implements MessageProvider{
    private final String message;

    public ConfigurableMessageProvider(@Value("Configurable message") String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}