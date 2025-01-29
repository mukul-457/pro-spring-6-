package ioc.injectCollections;

import ioc.nesting.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

public class CollectionInjectionDemo {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(CollectionConfig.class);
        CollectingBean cb = ctx.getBean(CollectingBean.class);
        cb.printAllSongs();
    }
}
@Component
class CollectingBean{
    @Autowired @Qualifier("songs")
    public List<Song> songsCollections;

    public void printAllSongs(){
        songsCollections.forEach((s)-> System.out.println(s.getTitle()));
    }
}