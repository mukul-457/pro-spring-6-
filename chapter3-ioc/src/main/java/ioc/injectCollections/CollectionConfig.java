package ioc.injectCollections;

import ioc.nesting.Song;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@ComponentScan
public class CollectionConfig {
    @Bean
    public List<Song> songs(){
        return List.of(
                new Song("list song 1"),
                new Song("list song 2")
        );
    }
    @Bean
    public Song song1(){
        return new Song("song 1");
    }

    @Bean
    public Song song2(){
        return new Song("song 2");
    }
}
