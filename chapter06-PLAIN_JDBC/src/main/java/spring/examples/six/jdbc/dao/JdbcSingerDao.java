package spring.examples.six.jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
@Component("first")
public class JdbcSingerDao implements SingerDao , InitializingBean , BeanNameAware {
    private static Logger LOGGER = LoggerFactory.getLogger(JdbcSingerDao.class);
    private DataSource dataSource;
    private String name;
    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null){
            throw  new RuntimeException("dataSource must be set for JdbcSingerDao");
        }
    }
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public Optional<String> findNameById(Long id) {
        try(var connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement("SELECT * from SINGER where id =" + id);
            ResultSet resultSet = statement.executeQuery();
            String name = null;
            while (resultSet.next()){
                name = resultSet.getString("first_name")
                        + " " +  resultSet.getString("LAST_NAME");
            }
            return Optional.ofNullable(name);
        }catch (SQLException e){
            LOGGER.error( name + " something went wrong in sql execution",e);
            return Optional.empty();
        }
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
