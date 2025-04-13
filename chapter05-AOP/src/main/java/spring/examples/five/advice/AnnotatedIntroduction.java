package spring.examples.five.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.ComponentScan;
import spring.examples.five.aspectjannotation.Dancer;
import spring.examples.five.aspectjannotation.Performer;

@Aspect
@ComponentScan
public class AnnotatedIntroduction {
    @DeclareParents(value = "spring.examples.five.pointcut.Singer+", defaultImpl = Dancer.class)
    public static Performer performer;
}
