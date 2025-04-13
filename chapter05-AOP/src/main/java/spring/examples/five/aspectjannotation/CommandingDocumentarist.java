package spring.examples.five.aspectjannotation;

import org.springframework.stereotype.Component;

@Component
public class CommandingDocumentarist extends Documentarist{
    @Override
    public void execute(){
        guitarist.sing();
        Guitar g = new Guitar();
        g.setBrand("Gibson");
        guitarist.sing(g);
        guitarist.sing(new Guitar());
        guitarist.talk();
    }
}
