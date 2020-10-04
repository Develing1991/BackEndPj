package project.study.projuct.domain.single;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.study.projuct.domain.BlendingType;
import project.study.projuct.domain.Product;
import project.study.projuct.dto.CreateProductRequestDto;
import project.study.projuct.dto.UpdateProductRequestDto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@DiscriminatorValue("COFFEE")
@NoArgsConstructor
public class Coffee extends Product {
    @Enumerated
    private BlendingType blendingType;

    public Coffee(CreateProductRequestDto createProductRequestDto) {
        this.setName(createProductRequestDto.getName());
        this.setContents(createProductRequestDto.getName());
        this.setPrice(createProductRequestDto.getPrice());
        this.setStockQuantity(createProductRequestDto.getStockQuantity());
        this.setViews(0);
        this.blendingType = createProductRequestDto.getBlendingType();
        this.setCate("COFFEE");
        this.setDeleteYn("N");
    }

    public void updateCoffee(UpdateProductRequestDto updateProductRequestDto) {
        this.setName(updateProductRequestDto.getName());
        this.setContents(updateProductRequestDto.getContents());
        this.setPrice(updateProductRequestDto.getPrice());
        this.setStockQuantity(updateProductRequestDto.getStockQuantity());
        this.blendingType = updateProductRequestDto.getBlendingType();
        this.setCate(updateProductRequestDto.getCate());
        this.setDeleteYn(updateProductRequestDto.getDeleteYn());
    }
}
