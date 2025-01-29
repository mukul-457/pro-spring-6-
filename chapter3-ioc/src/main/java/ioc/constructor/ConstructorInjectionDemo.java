package ioc.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

public class ConstructorInjectionDemo {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
        mr.render();
    }
}

@Configuration
@ComponentScan
class Config{ }
@Component("provider")
class HelloWorldMessageProvider implements MessageProvider{

    @Override
    public String getMessage() {
        return "Constructor Injection: Hello World";
    }
}

@Component("renderer")
class StandardOutMessageRenderer implements MessageRenderer{
    private  MessageProvider messageProvider;
    StandardOutMessageRenderer(MessageProvider messageProvider){
        this.messageProvider = messageProvider;
    }
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