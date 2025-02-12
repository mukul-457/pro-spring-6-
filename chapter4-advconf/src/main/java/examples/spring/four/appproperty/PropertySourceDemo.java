package examples.spring.four.appproperty;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Component;

public class PropertySourceDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        AppProperty ap = ctx.getBean(AppProperty.class);
        System.out.println(ap);
    }
}

@Configuration
@PropertySource("classpath:application.properties")
class AppConfig{
    @Autowired
    StandardEnvironment env;
    @PostConstruct
    void configurePriority(){
        ResourcePropertySource ps = (ResourcePropertySource)  env.getPropertySources().stream().filter(p -> p instanceof ResourcePropertySource ).findAny().orElse(null);
        if (ps != null) env.getPropertySources().addFirst(ps);
    }
    @Bean
    AppProperty appProperty(){
        return new AppProperty();
    }
}

@Component
class AppProperty {
    private String application_home;
    private String user_home;

    public String getUser_home() {
        return user_home;
    }


    @Autowired
    public void setUser_home(@Value("${user.home}") String user_home) {
        this.user_home = user_home;
    }

    public String getApplication_home() {
        return application_home;
    }
    @Autowired
    public void setApplication_home(@Value("${application.home}") String application_home) {
        this.application_home = application_home;
    }

    @Override
    public String toString() {
        return "AppProperty{" +
                "application_home='" + application_home + '\'' +
                ", user_home='" + user_home + '\'' +
                '}';
    }

}
