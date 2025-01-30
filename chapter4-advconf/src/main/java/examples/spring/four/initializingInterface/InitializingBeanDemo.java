package examples.spring.four.initializingInterface;

import examples.spring.four.initmethod.InitMethodDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class InitializingBeanDemo {

        private static Logger logger = LoggerFactory.getLogger(InitMethodDemo.class);
        public static void main(String[] args) {
            var ctx = new AnnotationConfigApplicationContext(interfaceConfig.class);
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

@Configuration
class interfaceConfig{

    @Bean
    Singer singer1(){
        Singer singer = new Singer();
        singer.setName("John Mayer");
        singer.setAge(43);
        return singer;
    }
    @Bean
    Singer singer2(){
        Singer singer = new Singer();
        singer.setName("Mukul");
        return singer;
    }
    @Bean
    Singer singer3(){
        Singer singer = new Singer();
        singer.setAge(32);
        return singer;
    }

}
@Component
class Singer implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(Singer.class);
    private static final String DEFAULT_NAME = "No name";
    private String name;
    private int age;

    public void setName(String name){
        logger.info("Calling set name for bean of type {}",Singer.class);
        this.name = name;
    }

    public  void  setAge(int age){
        logger.info("Calling set age for bean of type {}",Singer.class);
        this.age = age;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Initializing bean values using 'afterPropertiesSet()'");
        if (this.name == null){
            logger.info("Using default name");
            this.name = DEFAULT_NAME;
        }
        if (this.age == 0){
            throw new IllegalArgumentException(
                    "You must set the age properties for all beans of type " + Singer.class
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