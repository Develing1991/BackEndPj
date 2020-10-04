package project.study.projuct.domain.single;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.study.projuct.domain.Product;
import project.study.projuct.dto.CreateProductRequestDto;
import project.study.projuct.dto.UpdateProductRequestDto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("BOOK")
@NoArgsConstructor
public class Book extends Product {
    private String author;
    private String isbn;

    public Book(CreateProductRequestDto createProductRequestDto) {
        this.setName(createProductRequestDto.getName());
        this.setContents(createProductRequestDto.getName());
        this.setPrice(createProductRequestDto.getPrice());
        this.setStockQuantity(createProductRequestDto.getStockQuantity());
        this.setViews(0);
        this.author = createProductRequestDto.getAuthor();
        this.isbn = createProductRequestDto.getIsbn();
        this.setCate("BOOK");
        this.setDeleteYn("N");
    }

    public void updateBook(UpdateProductRequestDto updateProductRequestDto) {
        this.setName(updateProductRequestDto.getName());
        this.setContents(updateProductRequestDto.getContents());
        this.setPrice(updateProductRequestDto.getPrice());
        this.setStockQuantity(updateProductRequestDto.getStockQuantity());
        this.author = updateProductRequestDto.getAuthor();
        this.isbn = updateProductRequestDto.getIsbn();
        this.setCate(updateProductRequestDto.getCate());
        this.setDeleteYn(updateProductRequestDto.getDeleteYn());
    }
}
