package ioc.configuration;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class JavaConfigDemo {
    public static void main(String[] args) {
        BeanFactory ctx  = new AnnotationConfigApplicationContext(ConfigContext.class);
        MessageRenderer renderer = ctx.getBean("renderer", MessageRenderer.class);
        renderer.render();
    }
}

@Configuration
class  ConfigContext{
    @Bean
    public  MessageProvider provider(){
        return new HelloWorldMessageProvider();
    }

    @Bean
    public MessageRenderer renderer(){
        MessageRenderer mr = new StandardOutMessageRenderer();
        mr.setMessageProvider(provider());
        return mr;
    }
}

interface MessageRenderer{
    void render();
    void setMessageProvider(MessageProvider mp);
}

interface  MessageProvider{
    String getMessage();
}

class HelloWorldMessageProvider implements MessageProvider{

    @Override
    public String getMessage() {
        return "Hello from java Configuration world";
    }
}

class StandardOutMessageRenderer implements MessageRenderer{
    private MessageProvider messageProvider;
    @Override
    public  void setMessageProvider(MessageProvider messageProvider){
        this.messageProvider = messageProvider;
    }
    @Override
    public void render() {
        System.out.println(messageProvider.getMessage());
    }
}


