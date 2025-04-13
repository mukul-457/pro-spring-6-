package spring.examples.five.pointcut;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;


public class StaticMethodMatcherDemo {
    public static void main(String[] args) {
        GoodGuitarist g1 = new GoodGuitarist();
        GreatGuitarist g2 = new GreatGuitarist();

        Singer p1;
        Singer p2;

        StaticMethodMatcherPointcut pc = new StaticMMPDemo();
        Advice advice = new SimpleAroundAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(g1);
        p1 = (Singer) pf.getProxy();

        pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(g2);
        p2 = (Singer) pf.getProxy();

        p1.sing();
        p2.sing();
    }
}


class StaticMMPDemo extends StaticMethodMatcherPointcut {
    private  Logger logger = LoggerFactory.getLogger(StaticMMPDemo.class);
    @Override
    public ClassFilter getClassFilter(){
        return cls ->  {
            logger.info("Checking class [{}] for advice application ", cls.getName());
            return cls == GoodGuitarist.class;
        };
    }
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        logger.info("Checking method [{}] for for advice application ", method.getName());
        return "sing".equals(method.getName());
    }
}

class SimpleAroundAdvice implements MethodInterceptor{
    private Logger logger = LoggerFactory.getLogger(SimpleAroundAdvice.class);
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.info("Around advice invoked");
        Object retVal = invocation.proceed();
        logger.info("Done advice");
        return retVal;
    }

}
