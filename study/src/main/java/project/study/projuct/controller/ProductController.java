package project.study.projuct.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project.study.projuct.domain.Product;
import project.study.projuct.domain.single.Book;
import project.study.projuct.domain.single.Coffee;
import project.study.projuct.domain.single.Health;
import project.study.projuct.dto.CreateProductRequestDto;
import project.study.projuct.dto.FindProductResponseDto;
import project.study.projuct.dto.UpdateProductRequestDto;
import project.study.projuct.repository.ProductRepository;
import project.study.projuct.repository.ProductRepositoryCustom;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepositoryCustom productRepositoryCustom;
    private final ProductRepository productRepository;

    /**
     * 상품 생성 (리팩토링 필요)
     */
    @PostMapping("/product/{cate}")
    public Long createProduct(@PathVariable("cate")String cate,@RequestBody CreateProductRequestDto createProductRequestDto) {
        //return productRepositoryCustom.createProduct("BOOK", createProductRequestDto);
        return productRepositoryCustom.createProduct(cate, createProductRequestDto);
    }

    /**
     * 상품 조회 단건 (리팩토링 필요)
     */
    @GetMapping("/product/{cate}/{id}")
    public FindProductResponseDto findProduct(@PathVariable("cate")String cate, @PathVariable("id")Long id){
        //return productRepositoryCustom.findByCateAndId("BOOK", id);
        return productRepositoryCustom.findByCateAndId(cate, id);
    }

    /**
     * 상품 조회 리스트
     */
    @GetMapping("/product/{cate}")
    public Page<FindProductResponseDto> findProductList(@PathVariable("cate")String cate, Pageable pageable) {
        Page<FindProductResponseDto> map;
        if (cate.equals("BOOK")) {
            Page<Book> findByCateBook = productRepository.findBookByCate(cate, pageable);
            map = findByCateBook.map(book -> new FindProductResponseDto(book));
        } else if (cate.equals("HEALTH")) {
            Page<Health> findByCateHealth = productRepository.findHealthByCate(cate, pageable);
            map = findByCateHealth.map(health -> new FindProductResponseDto(health));
        }else{
            Page<Coffee> findByCateCoffee = productRepository.findCoffeeByCate(cate, pageable);
            map = findByCateCoffee.map(coffee -> new FindProductResponseDto(coffee));
        }
        return map;
    }

    /**
     * 상품 수정
     */
    @PostMapping("/product/{cate}/{id}")
    public Long updateProduct(@PathVariable("cate") String cate, @PathVariable("id") Long id,
                              @RequestBody UpdateProductRequestDto updateProductRequestDto) {
        return productRepositoryCustom.updateProduct(cate, id, updateProductRequestDto);
    }

    /**
     * 상품 삭제
     */
    @DeleteMapping("/product/{cate}/{id}")
    public Long deleteProduct(@PathVariable("cate") String cate, @PathVariable("id") Long id) {
        return productRepositoryCustom.deleteProduct(cate, id);
    }


}
