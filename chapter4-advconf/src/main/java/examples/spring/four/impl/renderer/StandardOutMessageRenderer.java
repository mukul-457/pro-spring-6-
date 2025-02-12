package examples.spring.four.impl.renderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import two.decoupled.MessageProvider;
import two.decoupled.MessageRenderer;

@Component
public class StandardOutMessageRenderer implements MessageRenderer {

    private static Logger logger = LoggerFactory.getLogger(StandardOutMessageRenderer.class);
    private MessageProvider messageProvider;

    @Override
    public void render() {
        logger.info(messageProvider.getMessage());
    }

    @Autowired
    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
