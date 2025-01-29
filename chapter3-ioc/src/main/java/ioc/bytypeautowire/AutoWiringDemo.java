package ioc.bytypeautowire;
/*
This example shows autowiring by specifying the dependencies
in constructor.Note that since name of the dependencies are not
specified, they are injected "byType".
This strategy breaks if there are two beans of the same type in
same context.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class AutoWiringDemo {
    private static Logger logger =  LoggerFactory.getLogger(AutoWiringDemo.class);
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AutoConfig.class);
        Target target =  ctx.getBean(Target.class);
        logger.info("Target created ? {}" , target != null);
        logger.info("Inject bar ? {}" , target.bar != null);
        logger.info("Injected fooOne {}", target.fooOne != null ? target.fooOne.id : "" );
        logger.info("Injected fooTwo {}", target.fooTwo != null ? target.fooTwo : "");
    }
}
@Configuration
@ComponentScan
class AutoConfig{ }

@Component
class Target{
    private static Logger logger  = LoggerFactory.getLogger(Target.class);
    Foo fooOne;
    Foo fooTwo;
    Bar bar;
    Target(){
        logger.info(" --> Target() called");
    }
    Target(Foo foo){
        this.fooOne = foo;
        logger.info(" --> Target(Foo) called");
    }
    Target(Foo foo, Bar bar){
        this.fooOne = foo;
        this.bar = bar;
        logger.info(" --> Target(Foo, Bar) called");
    }

}

@Component
class Foo{
    String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
}

@Component
class Bar{ }