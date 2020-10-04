package project.study.projuct.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.study.projuct.domain.BlendingType;
import project.study.projuct.domain.Product;
import project.study.projuct.domain.single.Book;
import project.study.projuct.domain.single.Coffee;
import project.study.projuct.domain.single.Health;

import java.time.LocalDateTime;

@Data
public class FindProductResponseDto {
    private String name;
    private String contents;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
    private BlendingType blendingType;
    private String trainer;
    private String cate;
    private String deleteYn;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public void setItems(Product product){
        name = product.getName();
        contents = product.getContents();
        price = product.getPrice();
        stockQuantity = product.getStockQuantity();
        deleteYn = product.getDeleteYn();
        createdDate = product.getCreatedDate();
        lastModifiedDate = product.getLastModifiedDate();
        cate = product.getCate();
    }
    public FindProductResponseDto(Book book) {
        setItems(book);
        author = book.getAuthor();
        isbn = book.getIsbn();
    }

    public FindProductResponseDto(Health health) {
        setItems(health);
        trainer = health.getTrainer();
    }

    public FindProductResponseDto(Coffee coffee) {
        setItems(coffee);
        blendingType = coffee.getBlendingType();
    }
    public FindProductResponseDto(Product product) {
        setItems(product);
    }
}
