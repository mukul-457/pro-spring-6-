package examples.spring.four.profiles;

import examples.spring.four.profiles.highschool.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("highschool")
public class HighSchoolConfig {
    @Bean
    FoodProviderService foodProvider(){
        return  new FoodProviderServiceImpl();
    }
}
