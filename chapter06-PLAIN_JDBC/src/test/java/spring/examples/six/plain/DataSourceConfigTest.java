package spring.examples.six.plain;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.examples.six.config.BasicDataSourceCfg;
import spring.examples.six.config.EmbeddedJdbcCfg;
import spring.examples.six.config.SimpleDataSourceCfg;
import spring.examples.six.config.SpringJdbcConfig;
import spring.examples.six.jdbc.dao.SingerDao;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataSourceConfigTest {
    private static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfigTest.class);

    @Test
    public void testSimpleDataSource() throws SQLException{
        var ctx = new AnnotationConfigApplicationContext(SimpleDataSourceCfg.class, SpringJdbcConfig.class);
        var ds = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(ds);
        testDataSource(ds);
        var singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        assertEquals("John Mayer" , singerDao.findNameById(1L).orElseGet(() -> "Not Found"));
        ctx.close();
    }

    @Test
    void testBasicDataSourceCfg() throws SQLException{
        var ctx = new AnnotationConfigApplicationContext(BasicDataSourceCfg.class, SpringJdbcConfig.class);
        DataSource ds = ctx.getBean(DataSource.class);
        assertNotNull(ds);
        testDataSource(ds);
        var singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        assertEquals("John Mayer" , singerDao.findNameById(1L).orElseGet(() -> "Not Found"));
        ctx.close();
    }

    @Test
    void testEmbeddedDataSource() throws SQLException{
        var ctx = new AnnotationConfigApplicationContext(EmbeddedJdbcCfg.class, SpringJdbcConfig.class);
        DataSource ds = ctx.getBean(DataSource.class);
        assertNotNull(ds);
        testDataSource(ds);
        var singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        assertEquals("John Mayer" , singerDao.findNameById(1L).orElse( "Not Found"));
        ctx.close();
    }

    private void testDataSource(DataSource dataSource) throws SQLException{

        try(var connection = dataSource.getConnection()){
            var statement = connection.prepareStatement("SELECT 1");
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                int mockVal = resultSet.getInt("1");
                assertEquals(1, mockVal);
            }
        }catch (Exception e){
            LOGGER.debug("Something unexpected happened ", e);
        }
    }


}
