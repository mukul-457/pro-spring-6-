package spring.examples.five.annotated;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.examples.five.advice.*;
import spring.examples.five.aspectjannotation.*;
import spring.examples.five.pointcut.Singer;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotatedAdviceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(AnnotatedAdviceTest.class);

    @Test
    void testBeforeAdviceV1(){
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfig.class, BeforeAdviceV1.class);
        ctx.refresh();
        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("beforeAdviceV1"));
        Documentarist documentarist = ctx.getBean(Documentarist.class);
        documentarist.execute();
        ctx.close();
    }

    @Test
    void testBeforeAdviceV2(){
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfig.class, BeforeAdviceV2.class);
        ctx.refresh();
        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("beforeAdviceV2"));
        Documentarist documentarist = ctx.getBean(Documentarist.class);
        documentarist.execute();
        ctx.close();
    }

    @Test
    void testBeforeAdviceV3(){
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfig.class, BeforeAdviceV3.class);
        ctx.refresh();
        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("beforeAdviceV3"));
        Documentarist documentarist = ctx.getBean(Documentarist.class);
        documentarist.execute();
        ctx.close();
    }

    @Test
    void testBeforeAdviceV4(){
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfig.class, BeforeAdviceV4.class);
        ctx.refresh();
        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("beforeAdviceV4"));
        Documentarist documentarist = ctx.getBean(Documentarist.class);
        documentarist.execute();
        ctx.close();
    }

    @Test
    void testAroundAdviceV2(){
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfig.class, AroundAdviceV2.class);
        ctx.refresh();
        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("aroundAdviceV2"));
        CommandingDocumentarist cd = ctx.getBean(CommandingDocumentarist.class);
        cd.execute();
        ctx.close();
    }

    @Test
    void TestAfterAdviceV1(){
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfig.class, AfterAdviceV1.class);
        ctx.refresh();
        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("afterAdviceV1"));
        var guitar = new Guitar();
        var guitarist = ctx.getBean(PretentiousGuitarist.class);
        guitarist.sing(guitar);
        LOGGER.info("-------");
        guitar.setBrand("MusicMan");
        assertThrows(IllegalArgumentException.class, () -> guitarist.sing(guitar),
                "unacceptable guitar !!");
        ctx.close();
    }

    @Test
    void TestAfterAdviceV2(){
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfig.class, AfterAdviceV2.class);
        ctx.refresh();
        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("afterAdviceV2"));
        var guitar = new Guitar();
        var guitarist = ctx.getBean(PretentiousGuitarist.class);
        guitarist.sing(guitar);
        LOGGER.info("-------");
        guitar.setBrand("MusicMan");
        //guitarist.sing(guitar);
        assertThrows(RejectedInstrumentException.class, () -> guitarist.sing(guitar),
                "unacceptable guitar !!");
        ctx.close();
    }

    @Test
    void TestIntroduction(){
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfig.class, AnnotatedIntroduction.class);
        ctx.refresh();

        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("annotatedIntroduction"));

        PretentiousGuitarist guitarist = ctx.getBean(PretentiousGuitarist.class);
        assertInstanceOf(Singer.class, guitarist);
        guitarist.sing();

        LOGGER.info("Proxy type : {}", guitarist.getClass().getName());

        assertInstanceOf(Performer.class, guitarist);
        Performer performer = (Performer) guitarist;
        performer.perform();
    }
}
