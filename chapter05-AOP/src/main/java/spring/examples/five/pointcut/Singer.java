package spring.examples.five.pointcut;

public interface Singer {
    void sing();

    default void sing(String key){
        System.out.println("Singing in key " + key);
    }
}

class GoodGuitarist implements Singer{

    @Override
    public void sing() {
        System.out.println("Average singing");
    }
}


class GreatGuitarist implements Singer{

    @Override
    public void sing() {
        System.out.println("Great Singing");
    }
}


class AverageGuitarist implements Singer{
    @Override
    public void sing(){
        System.out.println("Average singing");
    }
}