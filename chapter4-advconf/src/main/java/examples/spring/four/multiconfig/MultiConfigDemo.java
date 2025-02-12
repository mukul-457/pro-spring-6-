package examples.spring.four.multiconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

public class MultiConfigDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        MessageRenderer r =  ctx.getBean(MessageRenderer.class );
        r.render();
    }
}

@Configuration()
@ComponentScan
class ServiceConfig{ }

@Configuration
@Import(ServiceConfig.class)
class MainConfig{
    @Autowired
    MessageProvider provider;

    @Bean
    public  MessageRenderer renderer(){
        StandardOutMessageRenderer r = new StandardOutMessageRenderer();
        r.setMessageProvider(provider);
        return  r;
    }
}
@Service("provider")
class ConfigurableMessageProvider implements MessageProvider{
    private final String message;
    public ConfigurableMessageProvider(@Value("This is service provider. How can i help you !") String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}

class StandardOutMessageRenderer implements MessageRenderer{
    private MessageProvider messageProvider;

    @Override
    public void render() {
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}