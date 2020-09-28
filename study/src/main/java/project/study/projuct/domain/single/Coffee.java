package project.study.projuct.domain.single;

import lombok.Getter;
import lombok.Setter;
import project.study.projuct.domain.BlendingType;
import project.study.projuct.domain.Product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@DiscriminatorValue("COFFEE")
public class Coffee extends Product {
    @Enumerated
    private BlendingType blendingType;
}
