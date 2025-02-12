package example.spring.four;

import examples.spring.four.impl.provider.ProviderConfig;
import examples.spring.four.impl.renderer.RendererConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

import static org.junit.jupiter.api.Assertions.*;

public class MessageRenderOneIT {
    @Test
    void testConfig (){
        var ctx = new AnnotationConfigApplicationContext(ProviderConfig.class, RendererConfig.class);
        var messageProvider = ctx.getBean(MessageProvider.class);
        var messageRenderer = ctx.getBean(MessageRenderer.class);
        assertAll("messageTest",
                () -> assertNotNull(messageRenderer),
                () -> assertNotNull(messageProvider),
                () -> assertEquals(messageProvider, messageRenderer.getMessageProvider())
        );
        messageRenderer.render();
    }
}
