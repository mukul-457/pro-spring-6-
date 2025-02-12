package examples.spring.four.propertysource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

public class PropertySourceDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(PropertySourceConfig.class);
        MessageRenderer renderer = (MessageRenderer) ctx.getBean("renderer");
        renderer.render();
    }
}

@Configuration
@PropertySource(value = "classpath:message.properties")
class PropertySourceConfig{
    @Autowired
    Environment env;

    @Bean
    MessageProvider provider(){
        return  new ConfigurableMessageProvider(env.getProperty("message"));
    }

    @Bean
    @DependsOn("provider")
    @Scope("prototype")
    MessageRenderer renderer(){
        StandardOutMessageRenderer r = new StandardOutMessageRenderer();
        r.setMessageProvider(provider());
        return  r;
    }
}

class ConfigurableMessageProvider implements MessageProvider{
    private final String message;
    public ConfigurableMessageProvider(String message){
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
        if (messageProvider == null){
            throw new RuntimeException("render method called before setting MessageProvider");
        }
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