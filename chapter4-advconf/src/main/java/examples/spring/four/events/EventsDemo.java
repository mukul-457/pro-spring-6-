package examples.spring.four.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public class EventsDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(EventsConfig.class);
        EventPublisherBean worker = ctx.getBean(EventPublisherBean.class);
        worker.publish("Hello World !");
        ctx.close();
    }
}

@Configuration
@ComponentScan
class EventsConfig{ }

class MessageEvent extends ApplicationEvent{
    private final String message;
    public MessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}

@Component
class MessageEventListener implements ApplicationListener<MessageEvent>{
    private static Logger logger = LoggerFactory.getLogger(MessageEventListener.class);
    @Override
    public void onApplicationEvent(MessageEvent event) {
        logger.info("Message received: {}", event.getMessage());
    }

}

@Component
class EventPublisherBean  implements ApplicationContextAware{
    private ApplicationContext ctx ;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public void publish(String message){
        MessageEvent event = new MessageEvent(this, message);
        ctx.publishEvent(event);
    }
}