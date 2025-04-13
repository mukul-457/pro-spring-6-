package spring.examples.five.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import spring.examples.five.aspectjannotation.Guitar;
import spring.examples.five.aspectjannotation.RejectedInstrumentException;

@Aspect
@Component
public class AfterAdviceV2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfterAdviceV2.class);
    @Pointcut("execution(* spring.examples.five..sing*(spring.examples.five.aspectjannotation.Guitar)) && args(value)")
    public void singWithGuitar(Guitar value){}

    @AfterThrowing(value = "singWithGuitar(guitar)", argNames = "jp,guitar,ex", throwing = "ex")
    public void simpleAfterThrowAdvice(JoinPoint jp, Guitar guitar, IllegalArgumentException ex){
        var signature  = (MethodSignature) jp.getSignature();
        LOGGER.info("V2 > Executed {} from {} with guitar {}",
                signature,signature.getDeclaringTypeName(), guitar);
        if (ex.getMessage().contains("unacceptable guitar")){
            throw new RejectedInstrumentException(ex.getMessage(),ex);
        }
    }
}
