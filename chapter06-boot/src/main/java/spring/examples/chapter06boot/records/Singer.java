package spring.examples.chapter06boot.records;

import java.time.LocalDate;
import java.util.List;

public record Singer(Long id,
                     String firstName,
                     String lastName,
                     LocalDate birthDate,
                     List<Album> albums) {
}
