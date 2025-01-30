package examples.spring.four.initmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public class InitMethodDemo {
    private static Logger logger = LoggerFactory.getLogger(InitMethodDemo.class);
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(initConfiguration.class);
        getBean("singer1", ctx);
        getBean("singer3", ctx);
        getBean("singer2", ctx);
    }
    static Singer getBean(String beanName, ApplicationContext ctx){
        try{
            Singer bean = (Singer)ctx.getBean(beanName);
            logger.info("Found {}.",bean);
            return bean;
        }catch (BeanCreationException b){
            logger.error("An error occurred in bean configuration: " + b.getMessage());
            return null;
        }
    }
}

@Component
class Singer{
    private static Logger logger = LoggerFactory.getLogger(Singer.class);
    private static final String DEFAULT_NAME = "No Name";
    private String name;
    private int age;


    public void setAge(int age) {
        logger.info("Calling setAge for bean of class {}.",Singer.class);
        this.age = age;
    }

    public void setName(String name){
        logger.info("Calling setName for bean of class {}.",Singer.class);
        this.name = name;
    }

    public void init(){
        logger.info("Initializing bean");
        if (this.name == null){
            logger.info("Using default name");
            this.name = DEFAULT_NAME;
        }
        if(this.age == Integer.MIN_VALUE){
            throw new IllegalArgumentException(
                    "You must set age property for all beans of type " + Singer.class
            );
        }
    }
    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

@Configuration
class initConfiguration{
    @Bean(initMethod = "init")
    Singer singer1(){
        Singer singer = new Singer();
        singer.setName("John Mayer");
        singer.setAge(43);
        return singer;
    }

    @Bean(initMethod = "init")
    Singer singer2(){
        Singer singer = new Singer();
        singer.setName("Mukul");
        return singer;
    }

    @Bean(initMethod = "init")
    Singer singer3(){
        Singer singer = new Singer();
        singer.setAge(32);
        return singer;
    }

}