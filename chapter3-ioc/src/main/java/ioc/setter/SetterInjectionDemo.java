package ioc.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

public class SetterInjectionDemo {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        MessageRenderer mr =  ctx.getBean("renderer", MessageRenderer.class);
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
        return  "Setter Injection: Hello World !";
    }
}

@Component("renderer")
class StandardOutMessageRenderer implements MessageRenderer{
    private MessageProvider messageProvider;

    @Override
    public void render() {
        if(messageProvider == null){
            throw  new RuntimeException("Message Provider need to be set");
        }
        System.out.println(messageProvider.getMessage());
    }


    @Override
    public void setMessageProvider(MessageProvider provider) {
        System.out.println("Injecting the provider in renderer via setter");
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}