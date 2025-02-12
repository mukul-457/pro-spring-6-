package examples.spring.four.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileDemo {
    private static Logger logger = LoggerFactory.getLogger(ProfileDemo.class);
    public static void main(String[] args) {
        var profile = System.getProperty("spring.profiles.active");
        var ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles(profile);
        ctx.register(HighSchoolConfig.class , KindergartenConfig.class);
        ctx.refresh();
        FoodProviderService fs = ctx.getBean(FoodProviderService.class);
        fs.provideLunchSet()
                .forEach(food -> System.out.println(food.getName()));
    }
}
