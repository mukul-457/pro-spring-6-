package ioc.explicitnaming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class ExplicitNamingDemo {
    private static Logger logger = LoggerFactory.getLogger(ExplicitNamingDemo.class);
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(ExplicitConfig.class);
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(b->logger.debug(b));

        var simpleBeans = ctx.getBeansOfType(SimpleBean.class);
        simpleBeans.forEach((k,v)-> {
            var aliases = ctx.getAliases(k);
            if(aliases.length > 0){
                logger.debug("Aliases for {}", k);
                Arrays.stream(aliases).forEach(a -> logger.debug("\t {}",a));
            }
        });
    }
}

@Configuration
@ComponentScan
class ExplicitConfig{
    @Bean(name = "simpleBean2")
    public SimpleBean  getSimpleBean2(){
        return new SimpleBean();
    }

    @Bean(name = {"simpleBean3", "three" ,"reserved"})
    public SimpleBean getSimpleBean3(){
        return new SimpleBean();
    }
}
@Component("simpleBean1")
class SimpleBean{

}