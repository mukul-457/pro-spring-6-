package two.decoupled;

public class HelloWorldDecoupled {
    public static void main(String[] args) {
//        MessageProvider mp = new HelloWorldMessageProvider();
//        MessageRenderer mr = new StandardOutMessageRenderer();
        MessageSupportFactory supportFactory = MessageSupportFactory.getInstance();
        MessageProvider mp = supportFactory.getMessageProvider().orElseThrow(() -> new IllegalArgumentException("provider not available"));
        MessageRenderer mr = supportFactory.getMessageRenderer().orElseThrow(()-> new IllegalArgumentException("renderer not provider"));
        mr.setMessageProvider(mp);
        mr.render();
    }
}
