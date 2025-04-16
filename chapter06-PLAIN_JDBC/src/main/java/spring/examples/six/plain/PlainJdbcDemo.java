package spring.examples.six.plain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.examples.six.plain.dao.pojos.PlainSingerDao;
import spring.examples.six.plain.dao.pojos.SingerDao;
import spring.examples.six.plain.pojos.Singer;

public class PlainJdbcDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlainJdbcDemo.class);
    private static final SingerDao singerDao = new PlainSingerDao();
    static {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            LOGGER.error("Problem loading DB driver ", e);
        }
    }

    public static void main(String[] args) {
        var singers = singerDao.findAll();
        for(Singer singer: singers){
            LOGGER.info(singers.toString());
        }
    }
}
