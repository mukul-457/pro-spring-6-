package spring.examples.chapter06boot.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spring.examples.chapter06boot.records.Singer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("singerRepo")
public class JdbcSingerRepo implements SingerRepo{
    public static final String ALL_SELECT = "SELECT * FROM SINGER";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Stream<Singer> findAll() {
        return  jdbcTemplate.queryForStream(
                ALL_SELECT, (rs, rid) -> {
                    return new Singer(rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birth_date").toLocalDate(),
                            List.of());
                }
        );
    }

    @Override
    public Optional<String> findFirstNameById(Long id) {
        return Optional.empty();
    }
}
