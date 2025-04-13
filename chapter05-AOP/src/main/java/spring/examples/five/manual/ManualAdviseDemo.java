package spring.examples.five.manual;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ManualAdviseDemo {
    public static void main(String[] args) {
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleAfterAdvice());
        pf.addAdvice(new SimpleBeforeAdvice());
        pf.addAdvice(new SimpleAroundAdvice());
        pf.setTarget(new Concert());
        Performance proxy = (Performance) pf.getProxy();
        proxy.execute();
    }
}



class SimpleBeforeAdvice implements MethodBeforeAdvice{
    private Logger logger = LoggerFactory.getLogger(SimpleBeforeAdvice.class);
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        logger.info("Before advice : ");
    }
}

class  SimpleAfterAdvice implements AfterReturningAdvice{
    private Logger logger = LoggerFactory.getLogger(SimpleAfterAdvice.class);
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        logger.info("After advice invoked");
    }
}

class SimpleAroundAdvice implements MethodInterceptor{
    private Logger logger = LoggerFactory.getLogger(SimpleAroundAdvice.class);
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.info("Around Advice invoked");
        StopWatch sw = new StopWatch();
        sw.start();
        Object rv = invocation.proceed();
        sw.stop();
        logger.info("Invocation  time: " + sw.getTotalTimeMillis());
        return  rv;
    }
}

class Concert implements  Performance{
    private Logger logger  = LoggerFactory.getLogger(Concert.class);
    @Override
    public void execute() {
        logger.info("La La La La ....");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }
}
interface Performance{
    void execute();
}
