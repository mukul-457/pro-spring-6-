package examples.spring.four.awarebeans;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;


public class AwareDemo {
    public static void main(String[] args) {
       var ctx =  new AnnotationConfigApplicationContext(AwareConfig.class);
       System.out.println(ctx.getBean(ContextAware.class).getDependencyBean() == null);
    }
}

@Configuration
@ComponentScan
class AwareConfig{ }
@Component
class Named implements BeanNameAware{
    private static Logger logger = LoggerFactory.getLogger(Named.class);
    private String name;
    @Override
    public void setBeanName(String name) {
        logger.debug("Bean of type {} created with name {}",Named.class,name);
        this.name = name;
    }
    @PreDestroy
    public void destroy(){
        logger.debug("Destroying bean {}",name);
    }
    public String getName(){
        return name;
    }

}


@Component
class  ContextAware implements ApplicationContextAware{
    private static Logger logger = LoggerFactory.getLogger(ContextAware.class);
    private ApplicationContext ctx;
    private Named dependencyBean;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("Setting application context to bean of type {}",ContextAware.class);
        this.ctx = applicationContext;
        if (applicationContext instanceof GenericApplicationContext){
            ((GenericApplicationContext)  applicationContext).registerShutdownHook();
        }
        setDependencyBean();
    }
    //@DependsOn("named")
    public void setDependencyBean(){
        dependencyBean =  ctx.getBean(Named.class);
        logger.debug("Injected the dependency bean \"{}\" to the bean {}",dependencyBean.getName() ,ContextAware.class);
    }

    public Named getDependencyBean(){
        return dependencyBean;
    }

}