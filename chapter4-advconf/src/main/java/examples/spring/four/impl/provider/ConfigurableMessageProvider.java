package examples.spring.four.impl.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import two.decoupled.MessageProvider;

@Component
public class ConfigurableMessageProvider implements MessageProvider {
    private final String message;

    public ConfigurableMessageProvider(@Value("Text Sample") String message){
        this.message =  message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
