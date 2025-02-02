package examples.spring.four.messagesource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class MessageSourceDemo {
    private static Logger logger = LoggerFactory.getLogger(MessageSourceDemo.class);
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(MessageSourceConfig.class);
        Locale ukrainian = new Locale("uk", "UA");
        Locale hindi = new Locale("hi", "IN");

        logger.info(ctx.getMessage("msg", null, Locale.ENGLISH ));
        logger.info(ctx.getMessage("msg", null, ukrainian));
        logger.info(ctx.getMessage("msg", null, hindi));
        logger.info(ctx.getMessage("nameMsg", new Object[]{ "Mukul", "Acharya" }, Locale.ENGLISH));
        logger.info(ctx.getMessage("nameMsg", new Object[]{ "Mukul", "Acharya" }, ukrainian));
        logger.info(ctx.getMessage("nameMsg",new Object[]{ "Mukul",  "Acharya" }, hindi));


    }
}

class MessageSourceConfig{
    @Bean
    public MessageSource messageSource(){
        var msgSource = new ResourceBundleMessageSource();
        msgSource.setBasename("labels");
        msgSource.setDefaultEncoding("UTF-8");
        return msgSource;
    }
}