package project.study.projuct.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "PRODUCT_SEQ_GENERATOR",sequenceName = "PRODUCT_SEQ"
                    ,initialValue = 1,allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PRODUCT_SEQ_GENERATOR")
    @Column(name = "product_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @Lob
    private String contents;

    private int views;
    //private Reply reply;

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int sampleQuantity = this.stockQuantity - quantity;
        if(sampleQuantity<0){
            throw new IllegalStateException("재고가 부족합니다.");
        }
        this.stockQuantity = sampleQuantity;
    }

}
