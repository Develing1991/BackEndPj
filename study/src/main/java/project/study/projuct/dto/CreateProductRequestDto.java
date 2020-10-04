package project.study.projuct.dto;

import lombok.Data;
import project.study.projuct.domain.BlendingType;

@Data
public class CreateProductRequestDto {
    private String name;
    private String contents;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
    private BlendingType blendingType;
    private String trainer;
}
