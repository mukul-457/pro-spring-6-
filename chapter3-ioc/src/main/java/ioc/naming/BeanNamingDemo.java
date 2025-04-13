package ioc.naming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

public class BeanNamingDemo {
    private static Logger logger = LoggerFactory.getLogger(BeanNamingDemo.class);
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(BeanNamingConfig.class);
        Arrays.stream(ctx.getBeanDefinitionNames())
                .forEach(beanName -> logger.debug(beanName));
        var multiBeans = ctx.getBeansOfType(MultiBean.class);
        System.out.println("[ " + multiBeans.getClass().getName() + " ]" );
        multiBeans.forEach((key, value) -> System.out.println(key));
    }
}

@Configuration
@ComponentScan(nameGenerator = SimpleBeanNameGenerator.class)
class BeanNamingConfig{
    @Bean
    public MultiBean multiBeanFromMethod(){
        return new MultiBean();
    }
}
@Component
class SimpleBean{

}

@Component
class MultiBean{

}
class SimpleBeanNameGenerator extends AnnotationBeanNameGenerator{
    @Override
    protected String  buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry){
        String beanName = definition.getBeanClassName().substring(definition.getBeanClassName().lastIndexOf(".")+1).toLowerCase(Locale.ROOT);
        String uid = UUID.randomUUID().toString().replace("-","").substring(0,8);
        return beanName + "-" + uid;
    }
}