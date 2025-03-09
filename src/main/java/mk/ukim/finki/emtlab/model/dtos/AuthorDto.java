package mk.ukim.finki.emtlab.model.dtos;

import lombok.Data;

@Data
public class AuthorDto {
    private String name;

    private String surname;

    private Long country;
}