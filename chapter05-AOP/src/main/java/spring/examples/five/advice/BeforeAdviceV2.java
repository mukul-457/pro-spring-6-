package spring.examples.five.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeAdviceV2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAdviceV1.class);

    @Pointcut("execution(* spring.examples.five..sing*(spring.examples.five.aspectjannotation.Guitar))")
    public void singMethod(){
        LOGGER.info("> Executing pointcut");
    }

    @Before("singMethod()")
    public void simpleBeforeAdvice(JoinPoint joinPoint){
        var signature = joinPoint.getSignature();
        LOGGER.info("V2 > Executing {} from {}" , signature.getName(), signature.getDeclaringTypeName());
    }
}
