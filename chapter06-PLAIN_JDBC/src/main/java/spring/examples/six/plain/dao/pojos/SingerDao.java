package spring.examples.six.plain.dao.pojos;

import spring.examples.six.plain.dao.CoreDao;
import spring.examples.six.plain.pojos.Singer;

import java.util.Optional;
import java.util.Set;

public interface SingerDao extends CoreDao {
    Set<Singer> findAll();
    Set<Singer> findByFirstName(String firstName);
    Set<Singer> findAllWithAlbums();
    Optional<String> findNameById(Long id);
    Optional<String> findLastNameById(Long id);
    Optional<String> findFirstNameById(Long id);
    Singer insert(Singer singer);
    void update(Singer singer);
    void delete(Singer singer);
    void insertWithAlbum(Singer singer);

}
