package mk.ukim.finki.emtlab.model.dtos;

import lombok.Data;
import mk.ukim.finki.emtlab.model.enums.Category;

@Data
public class BookDto {
    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;
}
