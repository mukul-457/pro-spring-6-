package spring.examples.five.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import spring.examples.five.aspectjannotation.Guitar;

@Aspect
@Component
public class AfterAdviceV1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfterAdviceV1.class);

    @Pointcut("execution(* spring.examples.five..sing*(spring.examples.five.aspectjannotation.Guitar)) && args(value)")
    public void singWithGuitar(Guitar value){}

    @After(value = "singWithGuitar(guitar)", argNames = "joinPoint,guitar")
    public void simpleAfterAdvice(JoinPoint joinPoint, Guitar guitar){
        var signature = joinPoint.getSignature();
        LOGGER.info("V1 > Executed {} from {} with arg {} ",
                signature, signature.getDeclaringTypeName(), guitar.getBrand());
    }
}
