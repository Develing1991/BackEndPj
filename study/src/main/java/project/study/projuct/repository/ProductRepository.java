package project.study.projuct.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.study.projuct.domain.Product;
import project.study.projuct.domain.single.Book;
import project.study.projuct.domain.single.Coffee;
import project.study.projuct.domain.single.Health;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Book> findBookByCate(String cate, Pageable pageable);
    Page<Health> findHealthByCate(String cate, Pageable pageable);
    Page<Coffee> findCoffeeByCate(String cate, Pageable pageable);
}
