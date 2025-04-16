package spring.examples.six.jdbc.templateDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import spring.examples.six.jdbc.dao.SingerDao;
import spring.examples.six.plain.pojos.Album;
import spring.examples.six.plain.pojos.Singer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class RowMapperDao implements SingerDao {
    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;
    @Override
    public Optional<String> findNameById(Long id) {
        return Optional.ofNullable(
                namedTemplate.queryForObject(
                        "SELECT CONCAT(first_name, ' ', last_name) FROM SINGER WHERE id = :singerId"
                        , Map.of("singerId", id)
                        , String.class
                        )
        );
    }

    public List<String> findAllNames(){
        return namedTemplate.queryForList("SELECT first_name FROM SINGER",Map.of(), String.class);
    }

    public Set<Singer> findAllWithAlbums(){
        String query = "select s.id, s.first_name, s.last_name, s.birth_date, "+
                "a.id AS album_id, a.title, a.release_date " +
                "from SINGER s " +
                "left join ALBUM a on s.id = a.singer_id";
        return namedTemplate.query(
                query, new WithAlbumExtractor()
        );
    }


    public Set<Singer> findAll(){
//        return new HashSet<>(namedTemplate.query("SELECT * FROM SINGER" , (rs, rid ) -> {
//            Singer s = new Singer();
//            s.setId(rs.getLong("id"));
//            s.setFirstName(rs.getString("first_name"));
//            s.setLastName(rs.getString("last_name"));
//            s.setBirthDate(rs.getDate("birth_date").toLocalDate());
//            s.setAlbums(Set.of());
//            return s;
//        }));

        return new HashSet<>(namedTemplate.query("SELECT * FROM SINGER", new SingerMapper()));

    }

    static class SingerMapper  implements RowMapper<Singer>{

        @Override
        public Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Singer s = new Singer();
            s.setId(rs.getLong("id"));
            s.setFirstName(rs.getString("first_name"));
            s.setLastName(rs.getString("last_name"));
            s.setBirthDate(rs.getDate("birth_date").toLocalDate());
            s.setAlbums(Set.of());
            return s;
        }
    }

    static class WithAlbumExtractor implements ResultSetExtractor<Set<Singer>>{

        @Override
        public Set<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Singer> singers = new HashMap<>();
            Singer singer;
            while (rs.next()){
                Long id = rs.getLong("id");
                singer = singers.get(id);
                if (singer == null){
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(rs.getString("first_name"));
                    singer.setLastName(rs.getString("last_name"));
                    singer.setBirthDate(rs.getDate("birth_date").toLocalDate());
                    singer.setAlbums(new HashSet<>());
                    singers.put(id, singer);
                }
                Long albumId = rs.getLong("album_id");
                if (albumId > 0){
                    Album album = new Album();
                    album.setId(albumId);
                    album.setTitle(rs.getString("title"));
                    album.setReleaseDate(rs.getDate("release_date").toLocalDate());
                    album.setSingerId(id);
                    singer.getAlbums().add(album);
                }
            }

            return new HashSet<>(singers.values());
        }
    }
}
