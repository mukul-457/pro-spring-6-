package examples.spring.four.jsrannotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
public class ConstructAnnotationDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        ctx.getBean("singer1");
    }
}

@Configuration
class AnnotationConfig{
    @Bean
    public  Singer singer1(){
        Singer singer = new Singer();
        singer.setAge(40);
        return  singer;
    }
}
@Component
class Singer{
    private static Logger logger = LoggerFactory.getLogger(Singer.class);
    private static final String DEFAULT_NAME = "No name";
    private String name;
    private int age;

    public void setName(String name){
        logger.info("Calling setName for the bean of type {}.", Singer.class);
        this.name = name;
    }

    public void setAge(int age){
        logger.info("Calling setAge() for the bean of type {}",Singer.class);
        this.age = age;
    }

    @PostConstruct
    private void verify() throws  Exception{
        logger.info("Initializing been with method identified by @PostConstruct");
        if (this.name == null){
            logger.info("Using default name");
            this.name = DEFAULT_NAME;
        }
        if (this.age == 0 ){
            throw  new IllegalArgumentException("You must set age property for all beans of type " + Singer.class);
        }
    }

}