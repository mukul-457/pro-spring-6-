package examples.spring.four.factorybean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.security.MessageDigest;

public class FactoryBeanDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(FactoryConfig.class);
        MessageDigester digester = ctx.getBean(MessageDigester.class);
        digester.digest("Hello Word !");
        ctx.close();
    }
}

@Configuration
@ComponentScan
class FactoryConfig{
    @Bean
    public MessageDigestFactoryBean shaDigest(){
        MessageDigestFactoryBean  shaDigest = new MessageDigestFactoryBean();
        shaDigest.setAlgorithmName("SHA1");
        return shaDigest;
    }

    @Bean
    public MessageDigestFactoryBean defaultDigest(){
        return new MessageDigestFactoryBean();
    }

    @Bean
    public MessageDigester messageDigester(@Qualifier("shaDigest") MessageDigest shaDigest,@Qualifier("defaultDigest") MessageDigest defaultDigest) throws  Exception{
        MessageDigester messageDigester = new MessageDigester();
        messageDigester.setDigest1(shaDigest);
        messageDigester.setDigest2(defaultDigest);
        return messageDigester;
    }
}


class MessageDigestFactoryBean implements FactoryBean<MessageDigest>, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(MessageDigestFactoryBean.class);
    private String algorithmName = "MD5";
    private MessageDigest messageDigest;
    @Override
    public MessageDigest getObject() throws Exception {
        logger.info("Providing the already instantiated object of type MessageDigest");
        return messageDigest;
    }

    @Override
    public Class<?> getObjectType() {
        return MessageDigest.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Setting the messageDigest instance at initialization");
        messageDigest = MessageDigest.getInstance(algorithmName);
    }

    public  void setAlgorithmName(String algorithmName){
        logger.info("Setting the algorithm name");
        this.algorithmName = algorithmName;
    }
}

class MessageDigester{
    private static final Logger logger = LoggerFactory.getLogger(MessageDigester.class);
    private MessageDigest digest1;
    private MessageDigest digest2;

    public MessageDigest getDigest1() {
        return digest1;
    }

    public void setDigest1(MessageDigest digest1) {
        this.digest1 = digest1;
    }

    public MessageDigest getDigest2() {
        return digest2;
    }

    public void setDigest2(MessageDigest digest2) {
        this.digest2 = digest2;
    }

    public void digest(String message){
        logger.info("Digesting message using digest1");
        digest(message, digest1);
        logger.info("Digesting message using digest2");
        digest(message,digest2);
    }

    private void digest(String message, MessageDigest digest){
        logger.info("Using algorithm: " + digest.getAlgorithm());
        digest.reset();
        byte[] bytes = message.getBytes();
        byte[] hashed = digest.digest(bytes);
        logger.info("Original message: {}", bytes );
        logger.info("Encrypted message: {}", hashed);
     }
}