package spring.examples.five.aspectjannotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import spring.examples.five.pointcut.Singer;

@Component("johnMayor")
public class GrammyGuitarist implements Singer {
    private static Logger LOGGER = LoggerFactory.getLogger(GrammyGuitarist.class);
    @Override
    public void sing() {
        LOGGER.info("singing: gravity is working against me\n" +
                "and gravity wants to bring me down");
    }

    public void sing(Guitar guitar){
        LOGGER.info("playing: " + guitar.play());
    }

    public void talk(){
        LOGGER.info("talking");
    }

    public void rest(){
        LOGGER.info("Resting");
    }
}
