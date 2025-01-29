package two.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import two.decoupled.HelloWorldMessageProvider;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;
import two.decoupled.StandardOutMessageRenderer;

@Configuration
public class HelloWorldConfiguration {
    @Bean
    public MessageProvider provider(){
        return new HelloWorldMessageProvider();
    }
    @Bean
    public MessageRenderer renderer(){
        MessageRenderer mr = new StandardOutMessageRenderer();
        mr.setMessageProvider(provider());
        return mr;
    }
}
