package spring.examples.five.aspectjannotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import spring.examples.five.pointcut.Singer;
@Component
public class PretentiousGuitarist implements Singer {
    private static final Logger  LOGGER = LoggerFactory.getLogger(PretentiousGuitarist.class);
    @Override
    public void sing() {
        LOGGER.info("I am Pretentious . LA..LA..LA");
    }

    public void sing(Guitar guitar){
        if (guitar.getBrand().equalsIgnoreCase("MusicMan")){
            throw  new IllegalArgumentException("unacceptable guitar");
        }
        LOGGER.info("Playing: " + guitar.play());
    }
}
