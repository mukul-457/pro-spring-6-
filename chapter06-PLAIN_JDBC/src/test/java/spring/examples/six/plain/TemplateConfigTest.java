package spring.examples.six.plain;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import spring.examples.six.config.PlainTemplateCfg;
import spring.examples.six.config.TemplateDaoConfig;
import spring.examples.six.jdbc.templateDao.RowMapperDao;
import spring.examples.six.jdbc.templateDao.TemplateSingerDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TemplateConfigTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateConfigTest.class);

    @Test
    void testPlainJdbcTemplate(){
        var ctx = new AnnotationConfigApplicationContext(PlainTemplateCfg.class, TemplateDaoConfig.class);
        var jdbcTemplate = ctx.getBean(JdbcTemplate.class);
        assertNotNull(jdbcTemplate);
        var singerDao = ctx.getBean(TemplateSingerDao.class);
        assertEquals("John Mayer" , singerDao.findNameById(1L).orElse("Not Found"));
    }

    @Test
    void testRowMapper(){
        var ctx = new AnnotationConfigApplicationContext(PlainTemplateCfg.class, TemplateDaoConfig.class);
        var namedTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
        assertNotNull(namedTemplate);
        var rowMapperDao = ctx.getBean(RowMapperDao.class);
        var singers = rowMapperDao.findAll();
        assertEquals(3, singers.size());
        singers.forEach(singer -> LOGGER.info(singer.toString()));
        var first_names = rowMapperDao.findAllNames();
        first_names.forEach(LOGGER::info);
    }
    @Test
    void testResultSetExtractor(){
        var ctx = new AnnotationConfigApplicationContext(PlainTemplateCfg.class, TemplateDaoConfig.class);
        var rowMapperDao = ctx.getBean(RowMapperDao.class);
        var singers = rowMapperDao.findAllWithAlbums();
        assertEquals(3, singers.size());
        singers.forEach(singer -> LOGGER.info(singer.toString()));
    }
}
