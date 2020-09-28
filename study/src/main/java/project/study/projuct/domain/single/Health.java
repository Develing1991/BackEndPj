package project.study.projuct.domain.single;

import lombok.Getter;
import lombok.Setter;
import project.study.projuct.domain.Product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("HEALTH")
public class Health extends Product {
    private String trainer;
}
