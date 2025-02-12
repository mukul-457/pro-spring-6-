package examples.spring.four.profiles;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class NoProfileDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(HighSchoolConfig.class, KindergartenConfig.class);
        Arrays.stream(ctx.getEnvironment().getActiveProfiles()).forEach(System.out::println);
        var foodProviderService = ctx.getBean("foodProvider", FoodProviderService.class);
        foodProviderService.provideLunchSet()
                .forEach(food -> System.out.println(food.getName()));
        ctx.close();
    }
}
