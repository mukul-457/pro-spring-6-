package two.decoupled;

import java.util.Optional;
import java.util.Properties;

public class MessageSupportFactory {
    private static MessageSupportFactory instance;
    private Properties props;
    private MessageRenderer renderer;
    private MessageProvider provider;

    private MessageSupportFactory(){
        props = new Properties();
        try{
            props.load(this.getClass().getResourceAsStream("/msf.properties"));
            String renderClass = props.getProperty("renderer.class");
            String providerClass = props.getProperty("provider.class");
            renderer = (MessageRenderer) Class.forName(renderClass)
                    .getDeclaredConstructor().newInstance();
            provider = (MessageProvider) Class.forName(providerClass)
                    .getDeclaredConstructor().newInstance();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance(){
        return  instance;
    }

    public Optional<MessageRenderer> getMessageRenderer(){
        return renderer != null ? Optional.of(renderer) : Optional.empty();
    }

    public  Optional<MessageProvider> getMessageProvider(){
        return provider != null ? Optional.of(provider) : Optional.empty();
    }

}
