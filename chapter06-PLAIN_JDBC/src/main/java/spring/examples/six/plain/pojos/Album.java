package spring.examples.six.plain.pojos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private Long id;
    private Long singerId;
    private String title;
    private LocalDate releaseDate;

}
