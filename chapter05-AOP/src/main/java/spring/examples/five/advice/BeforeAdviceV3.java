package spring.examples.five.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeAdviceV3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAdviceV3.class);

    @Pointcut("execution(* spring.examples.five..sing(spring.examples.five.aspectjannotation.Guitar))")
    public void isSing(){}

    @Pointcut("bean(john*)")
    public void isJohn(){}

    @Before("isSing() && isJohn()")
    public void simpleBeforeAdvice(JoinPoint joinPoint){
        var signature  = (MethodSignature) joinPoint.getSignature();
        LOGGER.info("V3 > Executing {}  from {}" , signature.getName(), signature.getDeclaringTypeName());
    }
}
