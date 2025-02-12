package examples.spring.four.profiles;

import examples.spring.four.profiles.kindergarten.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("kindergarten")
public class KindergartenConfig {
    @Bean
    FoodProviderService foodProvider(){
        return  new FoodProviderServiceImpl();
    }
}
