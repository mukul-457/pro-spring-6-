package spring.examples.five.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import spring.examples.five.aspectjannotation.Guitar;
@Aspect
@Component
public class AroundAdviceV2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(AroundAdviceV2.class);

    @Pointcut("execution(* spring.examples.five..sing*(spring.examples.five.aspectjannotation.Guitar)) && args(value)")
    public void singWithGuitar(Guitar value){}

    @Around("singWithGuitar(guitar)")
    public Object simpleAroundAdvice(ProceedingJoinPoint pjp,  Guitar guitar) throws Throwable{
        var signature = (MethodSignature) pjp.getSignature();
        LOGGER.info("V2 > Before executing {} from {} with argument {} ",
                pjp.getSignature(), signature.getDeclaringTypeName(),
                guitar.getBrand());
        Object retVal = pjp.proceed();
        LOGGER.info("V2 > After executing {} from {} with argument {} ",
                signature, signature.getDeclaringTypeName(), guitar.getBrand());
        return  retVal;
    }
}
