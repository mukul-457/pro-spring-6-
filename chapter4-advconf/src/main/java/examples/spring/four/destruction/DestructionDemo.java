package examples.spring.four.destruction;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DestructionDemo {
    public static void main(String[] args) throws Exception{
        var ctx = new AnnotationConfigApplicationContext(DestructionConfig.class);
        ctx.registerShutdownHook();
        Thread.sleep(5000);
    }
}

@Configuration
@ComponentScan
class DestructionConfig{
    @Bean(destroyMethod = "customDestroy")
    public DecoupledFileManager decoupledFileManager(){
        return  new DecoupledFileManager();
    }
}

class DecoupledFileManager{
    private static Logger logger = LoggerFactory.getLogger(DecoupledFileManager.class);
    private Path file;
    public DecoupledFileManager(){
        logger.info("Creating bean of type {}." , DecoupledFileManager.class);
        try{
            file = Files.createFile(Path.of("sample"));
        }catch (IOException e){
            logger.error("Could not create file ");
        }
    }

    private void customDestroy() throws IOException{
        logger.info("Destroying bean of type {}.", DecoupledFileManager.class);
        if (file  != null){
            Files.deleteIfExists(file);
        }
    }
}

@Component
class SpringInterfaceBean implements DisposableBean {
    private static  Logger logger = LoggerFactory.getLogger(SpringInterfaceBean.class);
    private  Path file;

    public SpringInterfaceBean(){
        logger.info("Creating bean of type {}",SpringInterfaceBean.class);
        try{
            file = Files.createFile(Path.of("Sample2"));
        }catch (IOException e){
            logger.error("Could not create file");
        }
    }
    @Override
    public void destroy() throws Exception {
        logger.info("Destroying bean of type {}",SpringInterfaceBean.class);
        if (file != null){
            Files.deleteIfExists(file);
        }
    }
}

@Component
class AnnotatedBean{
    private static Logger logger = LoggerFactory.getLogger(AnnotatedBean.class);
    private Path file;

    public AnnotatedBean(){
        logger.info("Creating bean of type {}", AnnotatedBean.class);
        try{
            file = Files.createFile(Path.of("Sample3"));
        }catch (IOException e){
            logger.error("Could not create file");
        }
    }

    @PreDestroy
    void annotatedDestroy() throws IOException{
        logger.info("Destroying bean of type {}",AnnotatedBean.class);
        if(file != null){
            Files.deleteIfExists(file);
        }
    }
}