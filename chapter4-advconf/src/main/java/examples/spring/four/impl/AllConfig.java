package examples.spring.four.impl;

import examples.spring.four.impl.provider.ConfigurableMessageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;
import two.decoupled.StandardOutMessageRenderer;

@Configuration
public class AllConfig {

    @Bean
    @Profile("dev")
    MessageProvider messageProvider(){
        return new ConfigurableMessageProvider("dev message");
    }

    @Bean
    MessageRenderer messageRenderer(){
        MessageRenderer messageRenderer = new StandardOutMessageRenderer();
        messageRenderer.setMessageProvider(messageProvider());
        return messageRenderer;
    }
}
