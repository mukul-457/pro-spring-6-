package spring.examples.five.pointcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Arrays;

public class DynamicMMPDemo {
    public static void main(String[] args) {
        GoodGuitarist g1 = new GoodGuitarist();
        GreatGuitarist g2 = new GreatGuitarist();
        AverageGuitarist g3 = new AverageGuitarist();

        ProxyFactory pf = new ProxyFactory();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new DynamicMMP(), new SimpleAroundAdvice());
        pf.addAdvisor(advisor);
        pf.setTarget(g1);
        GoodGuitarist proxy1 = (GoodGuitarist) pf.getProxy();

        pf = new ProxyFactory();
        pf.setTarget(g2);
        pf.addAdvisor(advisor);
        GreatGuitarist proxy2 = (GreatGuitarist) pf.getProxy();


        proxy1.sing("D");
        proxy1.sing("e");
        proxy1.sing("C");

        proxy2.sing("D");
        proxy2.sing("e");
        proxy2.sing("C");

        g2.sing("C");
    }
}

class DynamicMMP extends DynamicMethodMatcherPointcut{
    private Logger logger = LoggerFactory.getLogger(DynamicMMP.class);
    @Override
    public ClassFilter getClassFilter(){
        return cls -> {
             logger.info("Checking class [{}] for advise", cls.getName());
             return cls == GoodGuitarist.class;
        };
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass){
        logger.info("checking method [{}] for advise ", method.getName());
        return "sing".equalsIgnoreCase(method.getName());
    }
    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        logger.info("Checking method [{}]  with arguments: {}", method.getName(), Arrays.toString(args));
        if(args.length > 0){
            return "c".equalsIgnoreCase((String)args[0]);
        }
        return false;
    }
}