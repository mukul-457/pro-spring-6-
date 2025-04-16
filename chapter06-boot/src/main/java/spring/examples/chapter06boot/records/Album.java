package spring.examples.chapter06boot.records;

import java.time.LocalDate;

public record Album(Long id, String title, Long singerId, LocalDate releaseDate) {
}
