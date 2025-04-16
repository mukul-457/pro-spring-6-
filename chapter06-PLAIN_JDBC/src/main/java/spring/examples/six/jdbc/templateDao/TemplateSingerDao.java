package spring.examples.six.jdbc.templateDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.examples.six.jdbc.dao.SingerDao;

import java.util.Optional;
@Component
public class TemplateSingerDao implements SingerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<String> findNameById(Long id) {
        String name = jdbcTemplate.queryForObject(
                "SELECT CONCAT(first_name , ' ' , last_name) FROM SINGER where id = ?"
                , String.class , id);
        return Optional.ofNullable(name);
    }
}
