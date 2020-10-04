package project.study.projuct.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.study.projuct.domain.Product;
import project.study.projuct.domain.single.Book;
import project.study.projuct.domain.single.Coffee;
import project.study.projuct.domain.single.Health;
import project.study.projuct.dto.CreateProductRequestDto;
import project.study.projuct.dto.FindProductResponseDto;
import project.study.projuct.dto.UpdateProductRequestDto;

import javax.persistence.EntityManager;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductRepositoryCustom {
    private final EntityManager em;

    @Transactional
    public Long createProduct(String ptype,CreateProductRequestDto createProductRequestDto) {
        Product product;
        switch (ptype) {
            case "BOOK":
                product = new Book(createProductRequestDto);
                break;
            case "HEALTH":
                product = new Health(createProductRequestDto);
                break;
            case "COFFEE":
                product = new Coffee(createProductRequestDto);
                break;
            default:
                throw new IllegalStateException("해당 카테고리 상품이 존재하지 않습니다.");
        }
        em.persist(product);
        return product.getId();
    }

    public FindProductResponseDto findByCateAndId(String cate, Long id) {
        switch (cate) {
            case "BOOK":
                Book book = em.find(Book.class, id);
                return new FindProductResponseDto(book);
            case "HEALTH":
                Health health = em.find(Health.class, id);
                return new FindProductResponseDto(health);
            case "COFFEE":
                Coffee coffee = em.find(Coffee.class, id);
                return new FindProductResponseDto(coffee);
            default:
                throw new IllegalStateException("해당 카테고리 상품이 존재하지 않습니다.");
        }
    }

    @Transactional
    public Long updateProduct(String cate, Long id, UpdateProductRequestDto updateProductRequestDto) {
        switch (cate) {
            case "BOOK":
                Book book = em.find(Book.class, id);
                book.updateBook(updateProductRequestDto);
                break;
            case "HEALTH":
                Health health = em.find(Health.class, id);
                health.updateHealth(updateProductRequestDto);
                break;
            case "COFFEE":
                Coffee coffee = em.find(Coffee.class, id);
                coffee.updateCoffee(updateProductRequestDto);
                break;
        }
        return id;
    }

    @Transactional
    public Long deleteProduct(String cate, Long id) {
        switch (cate) {
            case "BOOK":
                Book book = em.find(Book.class, id);
                book.setDeleteYn("Y");
                break;
            case "HEALTH":
                Health health = em.find(Health.class, id);
                health.setDeleteYn("Y");
                break;
            case "COFFEE":
                Coffee coffee = em.find(Coffee.class, id);
                coffee.setDeleteYn("Y");
                break;
        }
        return id;
    }

}
