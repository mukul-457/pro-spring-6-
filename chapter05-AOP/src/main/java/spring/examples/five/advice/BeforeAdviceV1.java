package spring.examples.five.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeAdviceV1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAdviceV1.class);
    @Before("execution(* spring.examples.five..sing*(spring.examples.five.aspectjannotation.Guitar))")
    public void simpleBeforeAdvice(JoinPoint joinPoint){
        var signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(" > Executing: {} from {} ", signature.getName(), signature.getDeclaringTypeName());
    }
}


