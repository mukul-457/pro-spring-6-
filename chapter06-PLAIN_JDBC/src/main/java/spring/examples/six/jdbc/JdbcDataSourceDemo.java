package spring.examples.six.jdbc;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.examples.six.config.BasicDataSourceCfg;


import javax.sql.DataSource;
import java.sql.SQLException;


public class JdbcDataSourceDemo {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        var ctx = new AnnotationConfigApplicationContext(BasicDataSourceCfg.class);
        DataSource ds = ctx.getBean(DataSource.class);
        var con = ds.getConnection();
    }
    public static void testManualBasic() throws SQLException{
        BasicDataSource bds  = new BasicDataSource();
        bds.setDriverClassName("org.mariadb.jdbc.Driver");
        bds.setUrl("jdbc:mariadb://localhost:3307/musicdb?useSSL=false");
        bds.setUsername("prospring6");
        bds.setPassword("prospring6");
        var connection = bds.getConnection();
        var statement  = connection.prepareStatement("SELECT * from SINGER where id = 1");
        var resultset = statement.executeQuery();
        while (resultset.next()){
            System.out.println(
                    resultset.getString("first_name") + " " + resultset.getString("last_name")
            );
        }
    }

}
