package examples.spring.four.resources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class ResourceDemo {
    private static Logger logger = LoggerFactory.getLogger(ResourceDemo.class);
    public static void main(String[] args)  throws Exception{
        var ctx = new AnnotationConfigApplicationContext();

        File basedir = new File(System.getProperty("java.io.tmpdir"));
        logger.info("Base dir: " + basedir.getAbsolutePath());
        Path filePath = Files.createFile(Path.of(basedir.getAbsolutePath(),"test.txt"));
        Files.writeString(filePath , "Hello World! ");
        filePath.toFile().deleteOnExit();

        Resource resource1 = ctx.getResource("file:///" + filePath);
        displayInfo(resource1);

        Resource resource2 = ctx.getResource("classpath:test.txt");
        displayInfo(resource2);

        Resource resource3 = ctx.getResource("https://iuliana-cosmina.com");
        displayInfo(resource3);
    }

    public static void displayInfo(Resource res) throws  Exception{
        logger.info("Resource class: {}", res.getClass() );
        logger.info("Resource URL content: {}",
                new BufferedReader(new InputStreamReader((InputStream)
                res.getURL().getContent())).lines().parallel().collect(Collectors.joining("\n")));
        logger.info("--------");
    }
}
