package spring.examples.five.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import spring.examples.five.aspectjannotation.Guitar;

@Aspect
@Component
public class BeforeAdviceV4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAdviceV4.class);

    @Pointcut("execution(* spring.examples.five..sing*(spring.examples.five.aspectjannotation.Guitar)) && args(value)")
    public void singWithGuitarOnly(Guitar value){
    }

    @Before("singWithGuitarOnly(guitar)")
    public void simpleBeforeAdvice(JoinPoint joinPoint,  Guitar guitar){
        if (guitar.getBrand().equalsIgnoreCase("Gibson")){
            LOGGER.info("V4 > Executing {}  from {}", joinPoint.getSignature(), joinPoint.getSignature().getDeclaringTypeName());
        }
    }
}
