package spring.examples.chapter06boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlMergeMode;
import spring.examples.chapter06boot.repos.SingerRepo;
@SpringBootTest(classes = {Chapter06BootApplication.class})
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql({"classpath:h2/drop-schema.sql", "classpath:h2/create-schema.sql"})
@ActiveProfiles("test")
class Chapter06BootApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(Chapter06BootApplication.class);
    @Autowired
    SingerRepo singerRepo;
    @Test
    @DisplayName("should return all singers")
    @Sql(value = "classpath:h2/test-data.sql"
            , config = @SqlConfig(encoding = "utf-8"
            ,separator = ";", commentPrefix = "--")
            ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    void testFindALl(){
        singerRepo.findAll().forEach(s -> LOGGER.info(s.toString()));
    }

}
