package ioc.nesting;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContextNestingDemo {
    public static void main(String[] args) {
        //if context is created with this approach , it cannot be modified again and refresh cannot be called
        var parentCtx = new AnnotationConfigApplicationContext(ParentConfig.class);
        // with this approach , context can be modified before calling refresh of context.
        var childCtx = new AnnotationConfigApplicationContext();
        childCtx.register(ChildConfig.class);
        childCtx.setParent(parentCtx);
        childCtx.refresh();
        Song s1 = childCtx.getBean("song1" , Song.class);
        Song s2 = childCtx.getBean("song2" , Song.class);
        Song s3 = childCtx.getBean("song3" , Song.class);
        System.out.println(s1.getTitle());
        System.out.println(s2.getTitle());
        System.out.println(s3.getTitle());
    }
}
