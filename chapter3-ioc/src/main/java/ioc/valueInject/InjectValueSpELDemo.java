package ioc.valueInject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

public class InjectValueSpELDemo {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppContextConfig.class);
        Dependent dp =  ctx.getBean(Dependent.class);
        System.out.println(dp);
        Dependent2 dp2 = ctx.getBean(Dependent2.class);
        System.out.println(dp2);
    }
}

@Configuration
@ComponentScan
class AppContextConfig{ }

@Component("valueConfig")
class ValueConfig{
    private String name = "John Mayer";
    private int age = 40;
    private float height = 1.42f;
    private  boolean developer = true;
    private Long ageInSeconds = 1_245_354L;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public boolean isDeveloper() {
        return developer;
    }

    public Long getAgeInSeconds() {
        return ageInSeconds;
    }
}
@Component
class Dependent2{
    private Dependent dp1;
    private Dependent dp2;
    Dependent2(Dependent dp ){
        System.out.println("constructor called 1 with dependent object");
        this.dp1 = dp;
    }


    Dependent2(Dependent dp1, Dependent dp2){
        System.out.println("constructor called with 2 dependent object");
        this.dp1 = dp1;
        this.dp2 = dp2;
    }

}
@Component
class Dependent{
    @Value("#{valueConfig.name.toUpperCase()}")
    private String name;
    @Value("#{valueConfig.age + 1}")
    private int age;
    @Value("#{valueConfig.height}")
    private float height;
    private  boolean developer;
    private  long ageInSeconds;

    @Override
    public String toString() {
        return "Dependent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}