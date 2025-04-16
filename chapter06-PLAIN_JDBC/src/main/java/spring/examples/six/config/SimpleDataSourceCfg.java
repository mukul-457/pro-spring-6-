package spring.examples.six.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@PropertySource("classpath:db/jdbc.properties")
public class SimpleDataSourceCfg {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDataSourceCfg.class);
    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    @SuppressWarnings("unchecked")
    public DataSource dataSource(){
        try{
            var datasource = new SimpleDriverDataSource();
            Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName(driverClassName);
            datasource.setDriverClass(driver);
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
            LOGGER.info("datasource crated with {} {} {} {}", datasource.getDriver() , datasource.getUrl() , datasource.getUsername(), datasource.getPassword());
            return datasource;
        }catch (Exception e){
            LOGGER.error("DataSource bean could not be created", e);
            return null;
        }
    }
}
