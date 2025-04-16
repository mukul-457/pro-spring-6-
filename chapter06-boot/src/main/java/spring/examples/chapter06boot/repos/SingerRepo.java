package spring.examples.chapter06boot.repos;

import spring.examples.chapter06boot.records.Singer;

import java.util.Optional;
import java.util.stream.Stream;

public interface SingerRepo {
    Stream<Singer> findAll();
    Optional<String> findFirstNameById(Long id);
}
