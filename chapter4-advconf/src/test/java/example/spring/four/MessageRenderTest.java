package example.spring.four;

import org.junit.jupiter.api.Test;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;
import two.decoupled.StandardOutMessageRenderer;

import static org.mockito.Mockito.*;

public class MessageRenderTest {
    @Test
    void testStandardOutMessageRenderer(){
        MessageProvider mockProvider  = mock(MessageProvider.class);
        when(mockProvider.getMessage()).thenReturn("test message");

        MessageRenderer mr = new StandardOutMessageRenderer();
        mr.setMessageProvider(mockProvider);
        mr.render();
        verify(mockProvider, times(1)).getMessage();
    }
}
