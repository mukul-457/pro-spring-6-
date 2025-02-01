package examples.spring.four.propertyeditors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PropertyEditorDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(EditorConfig.class);
        DiverseValueContainer b = ctx.getBean(DiverseValueContainer.class);
        System.out.println(b.getDate());
        System.out.println(b.getTimed());
        System.out.println(b.getAuthorName());
        ctx.close();
    }
}

@Configuration
@ComponentScan
class EditorConfig{
    @Bean
    CustomEditorConfigurer customEditorConfigurer(){
        var custom = new CustomEditorConfigurer();
        custom.setPropertyEditorRegistrars(new PropertyEditorRegistrar[]{new CustomPropertyEditorRegistrar()});
        return  custom;
    }
}

class FullName{
    private String firstName;
    private String lastName;

    public FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Override
    public String toString() {
        return "FullName{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
@Component
class DiverseValueContainer{
    private static Logger logger = LoggerFactory.getLogger(DiverseValueContainer.class);
    private Date date;
    private String timed;
    private FullName authorName;

    public Date getDate() {
        return date;
    }

    public String getTimed() {
        return timed;
    }

    public FullName getAuthorName(){
        return authorName;
    }

    @Value("2025/31/01")
    public void setDate(Date date) {
        this.date = date;
    }

    @Value("     This needs to be trimmed    ")
    public void setTimed(String trimmed) {
        this.timed = trimmed;
        this.timed = "Hi " + this.timed + ".Done!";
    }

    @Value("Mukul Acharya")
    public void setAuthorName(FullName fullName){
        this.authorName = fullName;
    }

}

class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar{
    private static Logger logger = LoggerFactory.getLogger(CustomPropertyEditorRegistrar.class);
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        logger.info("Registering the custom property editor for Date and String class");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/dd/MM");
        registry.registerCustomEditor(Date.class , new CustomDateEditor(dateFormat, true));
        registry.registerCustomEditor(String.class , new StringTrimmerEditor(true ));
        registry.registerCustomEditor(FullName.class, new NamePropertyEditor());
    }
}

class NamePropertyEditor extends PropertyEditorSupport{
    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        String[]  fullName = text.split("\\s");
        setValue(new FullName(fullName[0], fullName[1]));
    }
}

