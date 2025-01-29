package ioc.bynameautowire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import java.util.UUID;

public class ByNameAutoWireDemo {
    private static Logger logger = LoggerFactory.getLogger(ByNameAutoWireDemo.class);
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(NameConfig.class);
        Target target =  ctx.getBean(Target.class);
        logger.info("Created Target ? {}" , target != null);
        logger.info("Injected bar ? {}", target.bar != null );
        logger.info("Injected fooOne ? {}", target.fooOne != null ? target.fooOne.id : "");
        logger.info("Injected fooTwo ? {}", target.fooTwo != null ? target.fooTwo.id : "");
    }
}
@Component
@ComponentScan
class NameConfig{
    @Bean
    public Foo anotherFoo(){
        return new Foo();
    }
}
@Component
class  Target{
    private static Logger logger = LoggerFactory.getLogger(Target.class);
    Foo fooOne;
    Foo fooTwo;
    Bar bar;
    @Autowired
    @Qualifier("anotherFoo")
    public void setFooOne(Foo fooOne) {
        logger.info("Injected fooOne");
        this.fooOne = fooOne;
    }
    @Autowired
    @Qualifier("foo")
    public void setFooTwo(Foo fooTwo) {
        logger.info("Injected fooTwo");
        this.fooTwo = fooTwo;
    }
    @Autowired
    public void setBar(Bar bar) {
        logger.info("Injected bar");
        this.bar = bar;
    }
}
@Component
class Foo{
    String id = UUID.randomUUID().toString().replace("-", "").substring(0,8);
}

@Component
class Bar{

}