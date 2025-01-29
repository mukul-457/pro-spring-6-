package two.decoupled;

import java.util.ServiceLoader;

public class HelloWorldWithServiceLoader {
    public static void main(String[] args) {
        ServiceLoader<MessageRenderer> slr = ServiceLoader.load(MessageRenderer.class);
        ServiceLoader<MessageProvider> slp = ServiceLoader.load(MessageProvider.class);
        MessageRenderer mr = slr.findFirst().orElseThrow(() -> new IllegalArgumentException(""));
        MessageProvider mp = slp.findFirst().orElseThrow(() -> new IllegalArgumentException(""));
        mr.setMessageProvider(mp);
        mr.render();
    }
}
