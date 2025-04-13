package spring.examples.five.aspectjannotation;

public class Dancer implements Performer{
    @Override
    public void perform() {
        System.out.println("Shake it to the left, shake it to the right");
    }
}
