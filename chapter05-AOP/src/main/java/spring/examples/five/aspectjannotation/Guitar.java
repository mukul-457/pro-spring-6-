package spring.examples.five.aspectjannotation;

public class Guitar{
    private String brand = "Matt";

    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String play(){
        return "Em EM G C";
    }
}