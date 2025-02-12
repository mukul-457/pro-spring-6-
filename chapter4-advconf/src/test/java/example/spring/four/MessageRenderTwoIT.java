package example.spring.four;

import examples.spring.four.impl.provider.ProviderConfig;
import examples.spring.four.impl.renderer.RendererConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

import static org.junit.jupiter.api.Assertions.*;

public class MessageRenderTwoIT {
    private static ApplicationContext ctx;

    @BeforeAll
    static void setCtx(){
        ctx = new AnnotationConfigApplicationContext(ProviderConfig.class, RendererConfig.class);
    }

    @Test
    void testProvider(){
        var provider = ctx.getBean(MessageProvider.class);
        assertNotNull(provider);
    }

    @Test
    void testRenderer(){
        var renderer = ctx.getBean(MessageRenderer.class);
        assertAll("MessageTests",
                () -> assertNotNull(renderer),
                () -> assertNotNull(renderer.getMessageProvider())
        );
        renderer.render();
    }
}
