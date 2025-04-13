package spring.examples.four.runners;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

import java.util.Arrays;


@SpringBootApplication(scanBasePackages = "spring.examples.four.runners")
public class WithRunnersApplication {
    private static Logger logger = LoggerFactory.getLogger(WithRunnersApplication.class);
    public static void main(String[] args) {
        var ctx = SpringApplication.run(WithRunnersApplication.class, args);
        System.out.println("Listing all beans in the context");
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(logger::info);
    }
}


@Component
class StandardOutMessageRenderer implements MessageRenderer, CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(StandardOutMessageRenderer.class);
    private MessageProvider messageProvider;

    public StandardOutMessageRenderer(){
        logger.info("StandardOutMessageProvider() called");
    }
    @PostConstruct
    public void afterConstruct(){
        logger.info("afterConstruct() called");
    }
    @Override
    public void run(String... args) throws Exception {
        logger.info("run(String...args) called ");
        render();
    }

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
class ConfigurableMessageProvider implements MessageProvider, CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(ConfigurableMessageProvider.class);
    private String message;

    public ConfigurableMessageProvider(){
        logger.info("ConfigurableMessageProvider() called and message: {}" ,this.message);
    }
    @PostConstruct
    public void afterConstruct(){
        logger.info("afterConstruct() called and message: {}", this.message);
    }

    @Autowired
    public void  setMessage(@Value("Default Configurable message") String message){
        this.message = message;
        logger.info("setMessage(String message) called and message: {}", this.message);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 ){
            message = args[0];
        }
        logger.info("run(String...args) called and message: {}", this.message);
    }
    @Override
    public String getMessage() {
        return message;
    }
}