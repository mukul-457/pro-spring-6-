package two.decoupled;

public class HelloWorldMessageProvider implements MessageProvider{
    @Override
    public String getMessage() {
        return "Hello Decoupled World";
    }
}
