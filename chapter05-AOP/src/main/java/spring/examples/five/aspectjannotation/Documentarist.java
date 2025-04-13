package spring.examples.five.aspectjannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Documentarist {
    protected GrammyGuitarist guitarist;

    public void execute(){
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.talk();

    }
    @Autowired
    @Qualifier("johnMayor")
    public void setDep(GrammyGuitarist guitarist){
        this.guitarist = guitarist;
    }
}
