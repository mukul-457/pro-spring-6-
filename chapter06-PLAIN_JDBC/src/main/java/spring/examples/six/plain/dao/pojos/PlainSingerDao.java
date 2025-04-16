package spring.examples.six.plain.dao.pojos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.examples.six.plain.pojos.Singer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PlainSingerDao implements SingerDao{
    private static final Logger LOGGER = LoggerFactory.getLogger(PlainSingerDao.class);
    @Override
    public Set<Singer> findAll() {
        Set<Singer> result = new HashSet<>();
        try(var connection = getConnection();){
            var statement =  connection.prepareStatement("select * from SINGER");
            var resultset = statement.executeQuery();
            while(resultset.next()){
                Singer singer = new Singer();
                singer.setId(resultset.getLong("id"));
                singer.setFirstName(resultset.getString("first_name"));
                singer.setLastName(resultset.getString("last_name"));
                singer.setBirthDate(resultset.getDate("birth_date").toLocalDate());
                result.add(singer);
            }
        }catch (SQLException ex){
            LOGGER.error("problem while executing sql ", ex);
        }
        return result;
    }


    @Override
    public Set<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public Set<Singer> findAllWithAlbums() {
        return null;
    }

    @Override
    public Optional<String> findNameById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<String> findLastNameById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<String> findFirstNameById(Long id) {
        return Optional.empty();
    }

    @Override
    public Singer insert(Singer singer) {
        try ( var connection = getConnection()){
            var statement = connection.prepareStatement(
                    "insert into SINGER (first_name, last_name, birth_date) values (?, ?, ?)" , Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, singer.getFirstName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3,
                    java.sql.Date.valueOf(singer.getBirthDate()));
            statement.execute();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                singer.setId(generatedKeys.getLong(1));
            }
            return singer;
        } catch (SQLException ex) {
            LOGGER.error("Problem executing INSERT", ex);
        }
        return null;
    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Singer singer) {

    }

    @Override
    public void insertWithAlbum(Singer singer) {

    }
}
