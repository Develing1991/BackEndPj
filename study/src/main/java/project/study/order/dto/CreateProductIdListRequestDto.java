package project.study.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateProductIdListRequestDto<T> {
    List<T> productIds;
}
