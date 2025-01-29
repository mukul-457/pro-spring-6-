package ioc.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class SingerFieldInjectionDemo {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(Config3.class);
        Singer sonu = ctx.getBean(Singer.class);
        sonu.sing();
    }
}

@Configuration
@ComponentScan
class Config3{}

@Component
class Singer{
    @Autowired
    private  Inspiration inspiration;
    void sing(){
        System.out.println(inspiration.getLyrics());
    }
}
@Component
class Inspiration{

    //existing value gets overridden
    private String lyrics = "some song3 lyrics";

    //Constructor gets preference over setter
    public Inspiration(@Value("some song1 lyrics") String lyrics){
        this.lyrics = lyrics;
        System.out.println("lyrics set to " + this.lyrics);
    }

    {
        System.out.println("Existing lyrics " + this.lyrics);
    }
    public String getLyrics(){
        return lyrics;
    }
    public void setLyrics(@Value("song2 lyrics") String  lyrics){
        this.lyrics = lyrics;
    }
}