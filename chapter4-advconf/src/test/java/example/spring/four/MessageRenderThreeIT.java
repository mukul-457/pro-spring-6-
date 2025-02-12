package example.spring.four;

import examples.spring.four.impl.provider.ProviderConfig;
import examples.spring.four.impl.renderer.RendererConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RendererConfig.class, ProviderConfig.class})
public class MessageRenderThreeIT {
    @Autowired
    MessageRenderer messageRenderer;
    @Autowired
    MessageProvider messageProvider;

    @Test
    void testProvider(){
        assertNotNull(messageProvider);
    }

    @Test
    void testRenderer(){
        assertAll("messageTest",
                () -> assertNotNull(messageRenderer),
                () -> assertEquals(messageRenderer.getMessageProvider(), messageProvider)
        );
        messageRenderer.render();
    }
}

