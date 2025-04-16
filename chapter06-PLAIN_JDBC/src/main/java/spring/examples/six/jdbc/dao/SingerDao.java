package spring.examples.six.jdbc.dao;

import java.util.Optional;

public interface SingerDao {
    Optional<String> findNameById(Long id);
}
