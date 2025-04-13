package spring.examples.five.basic;

import org.aopalliance.aop.Advice;
import org.springframework.aop.*;

public class TestClass implements Advisor {
    @Override
    public Advice getAdvice() {
        return null;
    }
}

class TestPointCut implements PointcutAdvisor{

    @Override
    public Pointcut getPointcut() {
        return null;
    }

    @Override
    public Advice getAdvice() {
        return null;
    }
}

class TestIntro implements IntroductionAdvisor{

    @Override
    public ClassFilter getClassFilter() {
        return null;
    }

    @Override
    public void validateInterfaces() throws IllegalArgumentException {

    }

    @Override
    public Advice getAdvice() {
        return null;
    }

    @Override
    public Class<?>[] getInterfaces() {
        return new Class[0];
    }
}