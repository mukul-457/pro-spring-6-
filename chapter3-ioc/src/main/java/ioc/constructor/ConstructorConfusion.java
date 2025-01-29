package ioc.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component

public class ConstructorConfusion {
    private  String someValue;

    public ConstructorConfusion(String someValue){
        this.someValue = someValue;
    }
    @Autowired
    public ConstructorConfusion(@Value("9") int val){
        this.someValue = "valueInjected = " + (val + 10);
    }

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(ConstructorConfusion.class);
        ctx.refresh();
        ConstructorConfusion cbean =  ctx.getBean(ConstructorConfusion.class);
        System.out.println(cbean.someValue);


    }

}
