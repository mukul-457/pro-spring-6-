package ioc.methodinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
/*
This Demo is about Method Injection capability of Spring.
StandardLockOpener specifies dependency on KeyHelper using setter injection
AbstractLockOpener specifies dependency on LeyHelper using abstract method annotated with @Lookup
KeyHelper is a prototype bean. Spring creates a new object everytime it is required.
( everytime ctx.getBean  is called )
 */

public class MethodInjectionDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(LookupConfig.class);
        LockOpener std =  ctx.getBean("standardLockOpener", LockOpener.class);
        LockOpener abs =  ctx.getBean("abstractLockOpener" , LockOpener.class);
        displayInformation("StandardLockOpener" ,std);
        displayInformation("AbstractLockOpener" ,abs);
    }

    public  static void displayInformation(String beanType , LockOpener bean){
        KeyHelper k1 = bean.getMyKeyHelper();
        KeyHelper k2 = bean.getMyKeyHelper();
        System.out.println("[ " + beanType + " ]" + " KeyHelper instance same? " + (k1 == k2 ));
        StopWatch sw = new StopWatch();
        sw.start("Bean retrieval performance");
        for (int i = 0; i < 100_000; i++ ){
            KeyHelper key = bean.getMyKeyHelper();
            key.open();
        }
        sw.stop();
        System.out.println("100000 gets took " + sw.getTotalTimeMillis() + "ms");
    }
}

@Configuration
@ComponentScan
class LookupConfig {}


@Component("keyHelper")
@Scope("prototype")
class KeyHelper{
    private  static int id = 1;
    private int uid;
    KeyHelper(){
        this.uid = id;
        id++;
    }

    public int getUid() {
        return uid;
    }

    public void open(){

    }
}

interface LockOpener{
    KeyHelper getMyKeyHelper();
    void openLock();
}

@Component
class StandardLockOpener implements LockOpener{

    private KeyHelper keyHelper;
    @Override
    public KeyHelper getMyKeyHelper() {
        return keyHelper;
    }
    @Autowired
    void setKeyHelper(KeyHelper keyHelper){
        this.keyHelper = keyHelper;
    }
    @Override
    public void openLock() {
        KeyHelper key = getMyKeyHelper();
        System.out.println("KeyHelper " + key.getUid() +" received");
        key.open();
    }
}

@Component
/*this class and the lookup method does not need to be abstract.
Only thing required is @Lookup("beanName") annotation attached to the method to be replaced"
 */
abstract class AbstractLockOpener implements  LockOpener{
    @Lookup("keyHelper")
    @Override
    public abstract KeyHelper getMyKeyHelper();

    @Override
    public void openLock() {
        KeyHelper key = getMyKeyHelper();
        System.out.println("KeyHelper " + key.getUid() +" received");
        key.open();
    }
}