package project.study.projuct.domain.single;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.study.projuct.domain.Product;
import project.study.projuct.dto.CreateProductRequestDto;
import project.study.projuct.dto.UpdateProductRequestDto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("HEALTH")
@NoArgsConstructor
public class Health extends Product {
    private String trainer;

    public Health(CreateProductRequestDto createProductRequestDto) {
        this.setName(createProductRequestDto.getName());
        this.setContents(createProductRequestDto.getName());
        this.setPrice(createProductRequestDto.getPrice());
        this.setStockQuantity(createProductRequestDto.getStockQuantity());
        this.setViews(0);
        this.trainer = createProductRequestDto.getTrainer();
        this.setCate("HEALTH");
        this.setDeleteYn("N");
    }

    public void updateHealth(UpdateProductRequestDto updateProductRequestDto) {
        this.setName(updateProductRequestDto.getName());
        this.setContents(updateProductRequestDto.getContents());
        this.setPrice(updateProductRequestDto.getPrice());
        this.setStockQuantity(updateProductRequestDto.getStockQuantity());
        this.trainer = updateProductRequestDto.getTrainer();
        this.setCate(updateProductRequestDto.getCate());
        this.setDeleteYn(updateProductRequestDto.getDeleteYn());
    }
}
