package project.study.projuct.domain.single;

import lombok.Getter;
import lombok.Setter;
import project.study.projuct.domain.Product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("BOOK")
public class Book extends Product {
    private String author;
    private String isbn;
}
